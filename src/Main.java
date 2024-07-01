import java.util.ArrayList;

abstract class Employee{
    private String name;
    private int id;
    //create a parametrised constructor
    public Employee(String name, int id){
        this.name = name;
        this.id = id;
    }

    //ek getName ka method create kiya
    //if someone wants to know the name then he will use this method 
    //here we are using encapsulation
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }

    //now create abstract method 
    //abstract method ko implement ni krte bas body bna ke chod dete h
    public abstract double calculateSalary();

    //polymorphism me use krte h jab humari name same ho jate h aur functionality becomes different
    @Override
    public String toString(){
        return "Employee [name="+name+", id="+id+", salary="+calculateSalary()+"]";
    }
}

class FullTimeEmployee extends Employee{
    //now we are using the abstract method which is wrong 
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary){
        //becase we want to the oarent class constructor so use suoer keywrod 
         super(name, id);
         this.monthlySalary = monthlySalary;
    }
    @Override 

    public double calculateSalary(){
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee{
    //yaha pe error isliye aaya kyunki humne ek parttimeemployee ko extend karaya h
    //employee class se jo ki ek abstract class h

    private int hoursWorked;
    private double hourlyRate;

    //now creating a constructor 
    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate){
        //yaha pe name aur id ko parent class se lenge isliye super keyword use krenge 
        //hourlyRate aur hourlyworked ko normally
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

     //ab calculate salary ko overwrite krte h
     @Override
     public double calculateSalary(){
        //parttimeemployee ki salary calculate krne ke liye hoursWorked * hourlyRate
        return hoursWorked * hourlyRate;
     }
}

//till here 3 classes are made 

//4th class

//ArrayList<Integer> arr = new ArrayList<>();

class PayrollSystem{
    //list ek data structure hota h java me arraylist
    //list is a collection of different datatypes but list is not 
    //of defined size as that of a array

    private ArrayList<Employee> employeeList;

    //create constructor 
    public PayrollSystem(){
        //nayi arrayList create ho jayegi 
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        //this function is inbuilt in java which will add in the element
        employeeList.add(employee);   
    }

    //agar kuch add krna ho toh uska function likh diya par agar kuch remove krna ho toh 
    //id ki help se usko remove karenge 
    public void removeEmployee(int id){
        //ek variable banaya jisko remove krna h wo bhi Employee type ka 
        //integer type ki id se find karenge
        Employee employeeToRemove = null;

        for(Employee employee : employeeList){
              //employee ki id hum getId naam se nikal rhe h 
              //agar us employee ki id match kr gyi jisko hum dhoodh rhe 
              //usko variable me dal diya
              if(employee.getId() == id){
                employeeToRemove = employee;
                break;
              }
        }
        if(employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }
    }
    //if i want to see that how many persons are working in my organisation
    public void displayEmployee(){
        for(Employee employee : employeeList){
            System.out.println(employee);
        }
    }
}


public class Main {
    public static void main(String[] args)  {
        //PayrollSystem ka ek object bna denge 
        PayrollSystem payrollSystem = new PayrollSystem();
        FullTimeEmployee emp1 = new FullTimeEmployee("Vikas", 1,  70000.00);
        PartTimeEmployee emp2 = new PartTimeEmployee("Alexander",  2, 40, 100);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);

        System.out.println("Intial Employee Details : ");
        payrollSystem.displayEmployee();
        System.out.println("Removing Employees");
        payrollSystem.removeEmployee( 2);
        System.out.println("Remaining Employees Details: ");
        payrollSystem.displayEmployee();
    }
}
