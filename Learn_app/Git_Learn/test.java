class Student{
    // Attribute
    private int final STUDENT_ID;
    private String name;
    private int age;
    public static int nextID = 1;

    // Constructor
    public Student(name String, age int) {...}

    // Operation
    public String getName() {...}
    public void setName(newName String) {...}
    public int getAge() {...}
    protected setAge(newAge int) {...}
    void fallInLove() {...}
}

abstract class Employess{
    // Attribute
    private String name;

    // Constructor
    public Employee(name String) {...}

    // Operation
    public String getName() {...}
    public void setName(newName String) {...}

    // Abstract Operation
    public abstract void work();
}