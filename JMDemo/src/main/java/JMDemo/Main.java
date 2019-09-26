package JMDemo;

import static JMDemo.Personel.eachPersonel;
import static functionalj.list.FuncList.listOf;
import static functionalj.types.DefaultValue.TRUE;

import java.time.LocalDate;
import java.time.Month;

import functionalj.list.FuncList;
import functionalj.types.DefaultTo;
import functionalj.types.Nullable;
import functionalj.types.Struct;
import functionalj.types.choice.Self;

public class Main {
	
    @Struct
    void Personel(
    		String firstName, 
    		@Nullable
    		String middleName,
    		String lastName,
    		double salary,
    		LocalDate dateOfBirth,
    		@DefaultTo(TRUE)
    		boolean isOnSite) {}
    
    @Struct
    interface CompanySpec {
    	String             name();
    	FuncList<Personel> employees();
    	
    	default double totalSalary(Self self) {
    		Company company = self.unwrap();
    		return company.employees.mapToDouble(eachPersonel.salary).sum();
    	}
    }
    
    static public final Personel johnSmith 
    		= new Personel("John", null, "Smith", 120_000, LocalDate.of(1964, Month.JANUARY, 12), true);
    
    static public final Personel edwardMartin
    		= new Personel("Edwward", "Martin", 90_000, LocalDate.of(1964, Month.JANUARY, 12));
    
    static public final Personel ericClapton
    		= new Personel.Builder()
    		.firstName("Eric")
    		.middleName("Patrick")
    		.lastName("Clapton")
    		.salary(100_000)
    		.dateOfBirth(LocalDate.of(1945, Month.MARCH, 13))
    		.build();
    
    static public final Personel willSmith
			= new Personel.Builder()
			.firstName("Will")
			.lastName("Smith")
			.salary(100_000)
			.dateOfBirth(LocalDate.of(1968, Month.SEPTEMBER, 25))
			.build();
    
    
    static public final Company growth  = new Company("Growth",  listOf(johnSmith,   edwardMartin));
    static public final Company youWork = new Company("YouWork", listOf(ericClapton, willSmith));
    
    
    
	public static void main(String[] args) {
		// Data value
		

	    // Immutable
		
		
	    // Modification
		
		
	    // Validation
		
		
		// Null and default value
		
		
	    // Exhaustive builder
		
		
	    // Lens
		
		
	    // Schema
		
		
	    // To/From map
		
		
	}
}

