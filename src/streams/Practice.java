package streams;

import beans.Employee;
import beans.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practice {

    static void commonElements() {
        //list1 = {1,2,3,4,5}
        //list2 = {3,4,5,6,7}
        //Find out the common elements from the two lists (O/P - 3,4,5)

        Integer[] arr1 = {1,2,3,4,5};
        Integer[] arr2 = {3,4,5,6,7};

        //ineffective way
        Set<Integer> set = Stream.of(arr1)
                .filter(a1 -> Arrays.stream(arr2).anyMatch(a2 -> a1.equals(a2)))
                .collect(Collectors.toSet());
        System.out.println("common elements" + set);

    }

    static void occuranceOfChars() {
        String str = "I am a human being";
        str = "Programming";

      //  System.out.println(" map = "+ countMap);
    }

    static void occuranceOfElements(){
        List<Integer> list = Arrays.asList(4,1,4,3,3,2,3,4,4,2);

        Map<Integer, Long> countMap = list.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Map<Integer, Long> sortOnValue = list.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                        .entrySet().stream().sorted( (e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                        .collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue(),  (e1, e2) -> e1,   LinkedHashMap:: new));






        System.out.println(countMap);
        System.out.println(sortOnValue);
        //Output -> 1=2, 3=2, 4=1, 5=1}
    }


    static void topkOccurigWords() {
        String str = "the day is sunny day is sunny sunday sunny ";

        String[] strArr = str.split("\\s");

        Map<String, Long> topK =
        Arrays.asList(strArr).stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()) )
                .limit(3)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (entry1, entry2) -> entry1, LinkedHashMap::new));


       System.out.println("Top 3 occuring words = " + topK);

    }

    //to complete
    static void studentsHavingMaxMarksPerSection() {
        List<Student> students = List.of(
                new Student(90, "A", "1", List.of(12,8)),
                new Student(80, "B", "1", List.of(11,9)),
                new Student(75, "C", "1", List.of(10,6)),
                new Student(93, "P", "2", List.of(6,8)),
                new Student(70, "Q", "2", List.of(8,10)),
                new Student(75, "X", "3", List.of(3,2)),
                new Student(75, "Y", "3", List.of(4,5))
        );




       // System.out.println(st);


    }

    static void sortMapByValue() {

        Map<String, Integer> budget = new HashMap<>();
        budget.put("clothes", 120);
        budget.put("grocery", 150);
        budget.put("transportation", 100);
        budget.put("utility", 130);
        budget.put("rent", 1150);
        budget.put("miscellneous", 90);

        System.out.println(" map = "+budget);

        //Ascending Order
        Map<String, Integer> sortedBudget = budget.entrySet().stream()
                .sorted((entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()))
                .collect(Collectors.toMap(entry1 -> entry1.getKey(), entry1 -> entry1.getValue(), (entry1, entry2) -> entry1, LinkedHashMap::new ));

       System.out.println(" sorted map = "+ sortedBudget);

        //Descending Order
        Map<String, Integer> sortedBudgetReversed = budget.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .collect(Collectors.toMap(entry1 -> entry1.getKey(), entry1 -> entry1.getValue(),
                        (entry1, entry2) -> entry1, LinkedHashMap::new ));


        System.out.println(" reversed sorted map = "+ sortedBudgetReversed);


    }

    static void findSecondHighestSalaryEmployeeName() {
        List<Employee> empList = Arrays.asList(
                new Employee(222, "A", 2000, "Knp", "UP"),
                new Employee(111, "B", 3000, "Knp", "UP"),
                new Employee(333, "C", 4000, "Pune", "MH"),
                new Employee(333, "P", 4000, "Pune", "MH"),
                new Employee(123, "D", 5000, "Mumbai", "MH"),
                new Employee(123, "Z", 5000, "Mumbai", "MH"));

        //multiple employees having same salary (this will not work in that case)

        Map<Double, List<Employee>> sorted =
        empList.stream()
                .collect(Collectors.groupingBy(emp -> emp.getSalary()))
                .entrySet().stream()
                .sorted( (entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()) )
                .skip(1)
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue(), (entry1, entry2) -> entry1, LinkedHashMap::new));
        System.out.println("sorted -- " + sorted);

       // System.out.println(" 2nd Highest Salary Employee name = "+name); //Z



      //  System.out.println(" 2nd Highest Salary Employee name.... = "+em);  //C,P

    }

    static void firstSortSalaryThenName() {
        List<Employee> empList = Arrays.asList(
                new Employee(222, "A", 5000),
                new Employee(111, "C", 3000),
                new Employee(333, "B", 3000),
                new Employee(123, "A", 4000));


        List<Employee> sortedEmpList =
        empList.stream().sorted( (emp1, emp2) -> {
            double i = emp1.getSalary() - emp2.getSalary();

            if(i == 0){
                return emp1.getName().compareTo(emp2.getName());
            }
            return i>0 ? 1 : -1;
        }).collect(Collectors.toList());


      System.out.println(" sorted : "+sortedEmpList);


    }

    //Find duplicate elements
    static void prog5() {
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);

        Set<Integer> unique = new HashSet<>();
        Set<Integer> duplicates = list.stream().filter(i -> !unique.add(i)).collect(Collectors.toSet());

       System.out.println("duplicates" + duplicates);

        //Method - 2


        //System.out.println("duplicates2" + duplicates2);


    }

    static void prog_removeDuplicates(){
        List<String> list = Arrays.asList("Java", "C", "Python", "C", "Java", "Go");



       // System.out.println(distinctList);
        //Output -> [Java, C, Python, Go]
    }

    //find max/min
    static void findMinAndMaxFromIntList() {
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);

        int max = list.stream().max(Integer::compareTo).get();

        int min = list.stream().min(Integer::compareTo).get();

        System.out.println("max = "+ max + " min = "+ min);
    }

    //Sort list
    static void sortIntegerList() {
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);

        List<Integer> sortAsc = list.stream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("sortAsc = "+sortAsc);


        List<Integer> sortDesc = list.stream().sorted((i1, i2) -> i2.compareTo(i1) ).collect(Collectors.toList());
       System.out.println("sortDesc = "+sortDesc);

    }

    //limit skip
    static void prog8() {
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);





    }

    //Find Second lowest/highest number
    static void secondHighestAndLowestFromIntegerList() {


        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);

        int secLargest =  list.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
       System.out.println(" Second highest num = "+ secLargest);


        int secL =  list.stream().distinct().sorted(Integer::compareTo).skip(1).findFirst().get();
        System.out.println(" Second lowest num = "+ secL);


    }

    static void maxNElements(){
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);



       // System.out.println(max3);
        //Output -> [15, 10, 5]
    }

    static void minNElements(){
        List<Integer> list = Arrays.asList(2,10,2,5,15,10,2);


       // System.out.println(min3);
        //Output -> [2, 5, 10]
    }

    //Multiply elements of a list
    static void multiplyIntegerListElements() {
        List<Integer> list = Arrays.asList(2,10,2,5);

        int res =  list.stream().reduce((a,b) -> a*b).get();
        System.out.println(res);
    }

    //Square the integres and find average of those squares values that are less than 100
    static void prog4() {
        List<Integer> list = Arrays.asList(2,10,12,5,15);

        double avg = list.stream().map(a -> a*a).mapToInt(a -> a.intValue()).average().getAsDouble();
        System.out.println(" avg = "+avg);

    }

    //average of odd integers from int list
    static void prog2() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);

       // System.out.println(" average of "+list + "= "+ avg);
    }




    //Interview
    //from list of integers..create list that contains elements that start with number 1
    static void prog1() {

        List<Integer> list = Arrays.asList(10, 15, 5, 20, 25, 100);



       // System.out.println(list2);
    }

    static void prog3() {

        final class Employee{
            int id;
            int age;

            Employee(int id, int age){
                this.id = id;
                this.age = age;
            }
        }

        List<Employee> list = Arrays.asList(new Employee(123, 23), new Employee(345, 24), new Employee(567, 25));




        //System.out.println(sum);
        //System.out.println(sum2);

    }


    public static void main(String[] args) {
        // prog1();
        // prog2();
        // prog3();
         prog4();
        //prog5();
        //findMinAndMaxFromIntList();
       //  sortIntegerList();
        //prog8();
       //secondHighestAndLowestFromIntegerList();
        multiplyIntegerListElements();
       // findSecondHighestSalaryEmployeeName();
        //firstSortSalaryThenName();
        //sortMapByValue();
        //studentsHavingMaxMarksPerSection();
        //topkOccurigWords();
        //occuranceOfChars();
       // occuranceOfElements();
        //prog_removeDuplicates();
        //maxNElements();
        //minNElements();
        //commonElements();


    }
}
