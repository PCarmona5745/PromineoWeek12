import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoTest {
	private TestDemo testDemo;
	@BeforeEach
	void setUp() throws Exception {
		 this.testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.addPositive(a,b)).isEqualTo(expected);
		} else {
			assertThatThrownBy(()->testDemo.addPositive(a,b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	@SuppressWarnings("rawtypes")
	static Stream argumentsForAddPositive(){
		return Stream.of(arguments(2,4,6,false),arguments(1700,1995,3695,false),arguments(-75,5,-70,true)
				,arguments(0,5,5,false),arguments(-1,0,-1,true),arguments(0,0,0,false));
	}
	
	@Test
	void assertThatNumberSquaredIsCorrect(){
		TestDemo mockDemo = spy(testDemo);
		int desiredResult = 5;
		int result = 25;
		doReturn(desiredResult).when(mockDemo).getRandomInt();
		
		int desiredSquared = mockDemo.randomNumberSquared();
		
		assertThat(desiredSquared).isEqualTo(result);
	}

}
