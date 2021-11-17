package com.cov.beans;

public class Student {
int id;
String name;
public Student(int id, String name) {
	super();
	this.id = id;
	this.name = name;
	System.out.println("parameter constructor is called");
}
public Student() {
	super();
	System.out.println("no parameter constructor");
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
	System.out.println("invoking setId with:"+id);
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
	System.out.println("invoking setName with:"+name);
}

}
