package StructuralPatterns.FilterPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    private String name;
    private String gender;
    private String maritalStatus;

    public Person(String name, String gender, String maritalStatus) {
        this.name = name;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
}

interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}

class CriteriaMale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {

        List<Person> malePersons = new ArrayList<>();
        for(Person person : persons) {
            if(person.getGender().equalsIgnoreCase("Male")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}

class CriteriaFemale implements Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = persons.stream()
                .filter(p -> p.getGender().equalsIgnoreCase("Female"))
                .collect(Collectors.toList());
        return femalePersons;
    }
}

class CriteriaSingle implements  Criteria {

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersonsList = persons.stream()
                .filter(p -> p.getMaritalStatus().equalsIgnoreCase("Single"))
                .collect(Collectors.toList());
        return singlePersonsList;
    }
}

class AndCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public AndCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> personList = criteria.meetCriteria(persons);
        return otherCriteria.meetCriteria(personList);
    }
}

class OrCriteria implements Criteria {

    private Criteria criteria;
    private Criteria otherCriteria;

    public OrCriteria(Criteria criteria, Criteria otherCriteria) {
        this.criteria = criteria;
        this.otherCriteria = otherCriteria;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
        List<Person> secondCriteriaItems = otherCriteria.meetCriteria(persons);

        for(Person person: secondCriteriaItems) {
            if(!firstCriteriaItems.contains((person))) {
                firstCriteriaItems.add(person);
            }
        }
        return firstCriteriaItems;
    }
}

public class FIlterPattern {

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();

        persons.add(new Person("Robert","Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria male = new CriteriaMale();
        Criteria female = new CriteriaFemale();
        Criteria single = new CriteriaSingle();
        Criteria singleMale = new AndCriteria(male, single);
        Criteria singleOrFemale = new OrCriteria(female, single);

        System.out.println("Males");
        printPersons(male.meetCriteria(persons));

        System.out.println("Females");
        printPersons(female.meetCriteria(persons));

        System.out.println("Single Males");
        printPersons(singleMale.meetCriteria(persons));

        System.out.println("Single or Females");
        printPersons(singleOrFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons){

        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender() + ", Marital Status : " + person.getMaritalStatus() + " ]");
        }
    }
}
