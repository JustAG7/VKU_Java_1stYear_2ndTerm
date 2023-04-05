import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;
import java.awt.event.ActionListener;

public class Ex10 extends Frame implements ActionListener {
    private MenuBar bar;
    private Menu action;
    private MenuItem input,search,sort_view,exit;
    FileWriter f;
    PrintWriter pr;
    Vector list = new Vector();

    public Ex10(String s){
        super(s);
        loadData();
        bar = new MenuBar();
        setMenuBar(bar);
        action = new Menu("Action");
        bar.add(action);
        action.addActionListener(this);
        input = new MenuItem("Input");
        input.addActionListener(this);
        action.add(input);
        action.addSeparator();

        search = new MenuItem("Search");
        search.addActionListener(this);
        action.add(search);
        action.addSeparator();

        sort_view = new MenuItem("Sort & View");
        sort_view.addActionListener(this);
        action.add(sort_view);
        action.addSeparator();

        exit = new MenuItem("Exit");
        exit.addActionListener(this);
        action.add(exit);

        setSize(500,500);
        setVisible(true);
        setResizable(false);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Exit")){
            saveFile();
            System.exit(0);
        }
        if(e.getActionCommand().equals("Input")){
            InputForm input = new InputForm(this,"Input Form");
        }
        if(e.getActionCommand().equals("Sort and View")){
            sort();
        }
        if(e.getActionCommand().equals("Search")){
            new SearchForm("Search",list);
        }
    }
    public void updatelist(String n,String i,float a){
        Student st = new Student(n,i,a);
        list.add(st);
    }
    public void saveFile(){
        try{
            f = new FileWriter("/home/justa/lmao.txt");
            pr = new PrintWriter(f);
            Enumeration vEnum = list.elements();
            while(vEnum.hasMoreElements()){
                Student st = (Student)vEnum.nextElement();
                String toString = st.getName() + " " + st.getId() + " " + st.getAver();
                pr.println(toString);
                pr.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadData(){
        try{
            FileReader fr = new FileReader("/home/justa/lmao.txt");
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine())!= null) {
                String[] data = s.split("&");
                Student st = new Student(data[0],data[1], Float.parseFloat(data[2]));
                list.add(st);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
    public void sort(){
        Student[] sts = new Student[list.size()];
        int index=0;
        Enumeration vEnum = list.elements();

        while(vEnum.hasMoreElements()) {
            sts[index] = (Student)vEnum.nextElement();
            index++;
        }
        Arrays.sort(sts);
        TextArea ta = new TextArea("Name \t id \t aver\n");
        for(index =0; index < sts.length; index++) {
            ta.append(sts [index].getName()+"\t"+sts[index].getId()+"\t"+sts[index].getAver()+"\n");
        }
        this.add(ta);
        this.validate();
    }

    public static void main(String[] args) {
        new Ex10("Student Management");
    }
}
class Student implements Comparable{
    private String name;
    private String id;
    private float aver;
    public Student(String n,String i,float a){
        name = n;
        id = i;
        aver = a;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public float getAver(){
        return aver;
    }
    public int compareTo(Object o){
        Student st = (Student)o;
        return (int) (this.aver-((Student) o).aver);
    }
}
class InputForm extends Frame implements ActionListener{
    private Label namelb;
    private TextField name;
    private Label idlb;
    private TextField id;
    private Label averlb;
    private TextField aver;
    Button save,newInput,cancel;
    Ex10 main_frame;

    public InputForm(Ex10 a,String s){
        super(s);
        main_frame = a;
        setLayout(new BorderLayout());
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(3,2));

        namelb = new Label("Name");
        name = new TextField(20);
        idlb = new Label("ID");
        id = new TextField(20);
        averlb = new Label("Average");
        aver = new TextField(20);

        p1.add(namelb);
        p1.add(name);
        p1.add(idlb);
        p1.add(id);
        p1.add(averlb);
        p1.add(aver);
        this.add(p1,BorderLayout.NORTH);

        Panel p2 = new Panel();
        save = new Button("Save");
        save.addActionListener(this);
        newInput = new Button("New Input");
        newInput.addActionListener(this);
        cancel = new Button("Cancel");
        cancel.addActionListener(this);

        p2.add(save);
        p2.add(newInput);
        p2.add(cancel);
        this.add(p2,BorderLayout.SOUTH);

        setSize(300,300);
        setVisible(true);
        setResizable(false);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand().equals("Save")){
            main_frame.updatelist(name.getText(),id.getText(),Float.parseFloat(aver.getText()));
        }
        if(e.getActionCommand().equals("New Input")){
            reset();
        }
        if(e.getActionCommand().equals("Cancel")){
            this.dispose();
        }
    }
    public void reset(){
        name.setText("");
        id.setText("");
        aver.setText("");
    }
}
class SearchForm extends Frame implements ActionListener
{
    private Label namelb;
    private TextField name;
    private TextArea ta;

    private Button search, cancel;
    private Vector list;

    public SearchForm(String s, Vector v)
    {
        super(s);
        list = v;
        setLayout(new BorderLayout());
        Panel p1 = new Panel();
        p1.setLayout(new GridLayout(3,2));
        namelb= new Label("Name");
        name = new TextField(20);
        p1.add(namelb);
        p1.add(name);
        this.add(p1,"North");

        ta = new TextArea("Name \t Id \t Aver \n");
        this.add(ta,"Center");

        Panel p2 = new Panel();
        search = new Button("Search");
        cancel = new Button("Cancel");
        search.addActionListener(this);
        cancel.addActionListener(this);
        p2.add(search);
        p2.add(cancel);
        this.add(p2, "South");
        setSize(300,300);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Search"))
        {
            search(name.getText());
        }
        if(ae.getActionCommand().equals("Cancel"))
        {
            this.dispose();
        }
    }
    public void search (String s)
    {
        Enumeration vEnum= list.elements();
        while (vEnum.hasMoreElements())
        {
            Student st = (Student)vEnum.nextElement();
            if (st.getName().indexOf(s)!=-1)
            {
                ta.append(st.getName() +"\t"+st.getId() +"\t" + st.getAver()+"\n");

            }
        }
        this.validate();
    }

}




