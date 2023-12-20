public class Student {
    String name;
    String surname;
    double rating;

    public Student(String name, String surname, double rating) {
        this.name = name;
        this.surname = surname;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }
}
