package JMDemo.more;

import static JMDemo.more.MayBe.Just;
import static JMDemo.more.MayBe.Nothing;
import static JMDemo.more.Temperature.Fahrenheit;
import static JMDemo.utils.Console.print;
import static JMDemo.utils.Console.println;

import functionalj.types.Choice;
import functionalj.types.Nullable;
import functionalj.types.choice.Self;

public class MoreChoices {

    @Choice
    interface MayBeSpec<T> {
        void Just(T data);
        void Nothing();
    }
    
    @Choice
    interface EitherSpec<TR, TL> {
        void Left(TR rightData);
        boolean Right(TL leftData);
    }
    
    @Choice
    interface TrySpec<T> {
        void Success(T data);
        void Problem(Exception problem);
    }
    
    @Choice
    interface LinkedListSpec {
        void Nill();
        void Node(Object value, LinkedList rest);
    }
    
    @Choice
    interface TreeSpec {
        void Node(Object value, Tree left, Tree right);
        void Leaf(Object value);
    }
    
    @Choice
    interface LoginStatusSpec {
        void Login(@Nullable String userName);
        void Logout();
    }
    
    @Choice
    interface ReqestResultSpec<T> {
        void Success(T data);
        void Error(String errorMessage);
        void ConnectionFailed(Exception problem);
    }
    
    @Choice
    interface CommandSpec {
        void Forward(int distance);
        void Backward(int distance);
        void Turn(int angle);
        void Explode();
    }
    
    @Choice
    static interface TemperatureSpec {
        void Celsius(double celsius);
        void Fahrenheit(double fahrenheit);
        
        default Temperature.Fahrenheit toFahrenheit(Self self) {
            Temperature temp = self.unwrap();
            return temp.match()
                    .celsius   (c -> Temperature.Fahrenheit(c.celsius()*1.8 + 32.0))
                    .fahrenheit(f -> f);
        }
        default Temperature.Celsius toCelsius(Self self) {
            Temperature temp = self.unwrap();
            return temp.match()
                    .celsius   (c -> c)
                    .fahrenheit(f -> Temperature.Celsius((f.fahrenheit() - 32.0)/1.8));
        }
    }
    
    public static void main(String[] args) {
    	print("Just 5: ", Just(5));
		print("None:   ", Nothing());
		println();

        var tree  = Tree.Node("2", Tree.Leaf("2"), Tree.Leaf("3"));
        print("Tree: ", tree);
        
        var response = ReqestResult.Success("Cool!!!");
        var responseLength 
        		= response.match()
        		.success(success -> success.data().length())
        		.orElse(0);
        print("Response length: ", responseLength);
		println();
		
		var temperature = Fahrenheit(72);
		print("In celsius: ", temperature.toCelsius().celsius());
	}
}
