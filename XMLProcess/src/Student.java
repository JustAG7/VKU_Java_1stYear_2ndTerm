public class Student {
    private String name;
    private String classroom;
    private float score;
    private String phone;

    public Student(String name, String classroom, float score, String phone) {
        this.name = name;
        this.classroom = classroom;
        this.score = score;
        this.phone = phone;
    }
    public Student() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String toString() {
        return "Name: " + name + ", Classroom: " + classroom + ", Score: " + score + ", Phone: " + phone;
    }
}
