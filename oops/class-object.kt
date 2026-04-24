/*
   ! Class and Object in Kotlin :

   - Class is a blueprint/template for creating objects
   - Object is an instance of a class that can hold data and perform actions

   - To create a class, you use the "class" keyword followed by the class name
   - To create an object, you use the "object" keyword followed by the class name

   * syntax : 
        class ClassName {
            // class body
        }
        object ObjectName : ClassName() {
            // object body
        }
    
*/

//Code Example : 

class Person(val name : String , val age : Int){
    fun introduce(){
        println("My name is $name and I am $age years old");
    }
}

class Rectange(val height : Double , val width : Double){

    val area:Double
    get() = height * width // <- Custom getter: calculates area dynamically

    fun perimeter():Double{
        return 2 * (height + width);
    }
}


fun main(){
    val person = Person("Amit", 20);
    person.introduce();

    val rectangle = Rectange(4.0,3.0)
    println("Area : ${rectangle.area}"); // Auto invoked getter
    println("Perimeter : ${rectangle.perimeter()}");
}