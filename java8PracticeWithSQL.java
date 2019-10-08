public void java8PracticeWithSQL(){
		List employees = new ArrayList<>();
		Department d1 = new Department(1, "done");
		Department d2 = new Department(2, "dtwo");
		Department d3 = new Department(3, "dthree");

		employees.add(new Employee(1, "one", 24, "M", 24000.00, d1));
		employees.add(new Employee(2, "two", 26, "F", 30000.00, d2));
		employees.add(new Employee(3, "three", 28, "M", 32000.00, d2));
		employees.add(new Employee(4, "four", 24, "M", 28000.00, d3));
		employees.add(new Employee(5, "five", 25, "M", 24000.00, d1));
		employees.add(new Employee(6, "six", 23, "F", 32000.00, d2));

		// Select first_name, Last_Name from employee
		 employees.stream().map(e -> e.getEmployeeName() + " " +e.getGender()).forEach(System.out::println);

		// Select * from employee order by FIRST_NAME desc
		 employees.stream().map(e->e.getEmployeeName().toLowerCase()).sorted(Collections.reverseOrder()).forEach(System.out::println);
		 
		//Select * from employee order by FIRST_NAME asc,SALARY desc
		 employees.stream().sorted(Employee::compare).forEach(System.out::println);
		 
		// select * from employee where agee.getAge()<25).forEach(System.out::println); // Select * from EMPLOYEE where FIRST_NAME like 't%' or Get employee details from employee table whose first name starts with 't' 
		employees.stream().filter(e -> e.getEmployeeName().toLowerCase().startsWith("t")).forEach(System.out::println);
		
		//Select * from EMPLOYEE where Salary between 25000 and 30000
		employees.stream().filter(e-> e.getSalary()>=25000 && e.getSalary()<=30000).forEach(System.out::println); //select * from employee group by gender employees.stream().collect(Collectors.groupingBy(Employee::getGender)).forEach((key,value)->System.out.println(key+" "+value));
		
		//Select DEPARTMENT,sum(SALARY) Total_Salary from employee group by department
		Map<Department, Double> groupByEmployee = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingDouble(Employee::getSalary)));
		groupByEmployee.forEach((key,value)->System.out.println(key+" "+value));

                //Select DEPARTMENT,sum(SALARY) Total_Salary from employee group by DEPARTMENT having sum(SALARY) >30000 
		groupByEmployee.entrySet().stream().filter(e->e.getValue()>30000).forEach(e->System.out.println(e.getKey()+" "+e.getValue()));


		//Select DEPARTMENT,sum(SALARY) Total_Salary from employee group by DEPARTMENT order by Total_Salary descending
		//													OR
		//To Sort a map by value
		groupByEmployee.entrySet().stream().sorted(Map.Entry.<Department,Double>comparingByValue()).forEach((e->System.out.println(e.getKeygetValue())));

		Map<Integer,String> myMap=new HashMap<>();
		myMap.put(1, "one");
		myMap.put(2, "two");
		myMap.put(3, "three");
		myMap.put(4, "four");
		
		List<Map.Entry<Integertring>> myList=new ArrayList<>(myMap.entrySet());
		Collections.sort(myList,Map.Entry.<Integertring>comparingByValue());
		//								OR
		Collections.sort(myList,new Comparator<Map.Entry<Integertring>>(){

			@Override
			public int compare(Entry<Integer, String> o1, Entry<Integer, String> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		//select DEPARTMENT,max(SALARY) MaxSalary from employee group by DEPARTMENT order by MaxSalary asc
		employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(Collectors.
				maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get))).forEach((key,value)->System.out.println(key+" "+value));
                //or
                employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))).forEach((key,value)->System.out.println(key"+value));

          	// group by gender, uses 'mapping' to convert List to Set
employees.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.mapping(Employee::getEmployeeName, Collectors.toSet())))
               .forEach((key,value)->System.out.println(key+" "+value));
	}
