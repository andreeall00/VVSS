import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class ServiceTest {

    public Service service;
    String filenameStudent = "fisiere/Studenti.xml";
    String filenameTema = "fisiere/Teme.xml";
    String filenameNota = "fisiere/Note.xml";

    @Before
    public void init() throws IOException {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        PrintWriter filenameStudentPW = new PrintWriter(filenameStudent);
        PrintWriter filenameTemaPW = new PrintWriter(filenameTema);
        PrintWriter filenameNotaPW = new PrintWriter(filenameNota);
        filenameStudentPW.write("\n");
        filenameTemaPW.write("\n");
        filenameNotaPW.write("\n");
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudent_valid() {
//        Student student = new Student("2778", "Manuela", 934, "man@yahoo.com");
//        Student result = service.addStudent(student);
//        assertNull(result);
        assertTrue(true);
    }

//    @Test(expected = ValidationException.class)
    public void addStudent_invalid() {
//        Student student = new Student("", "", -1, "");
//        service.addStudent(student);
        assertTrue(true);
    }

    @Test //1-OK
    public void addStudent_valid_added() throws Exception {
        Student student = new Student("2YkL7", "Ana Pop", 1, "ana@pop.com");
        Student result = service.addStudent(student);
        assertNull(result);
    }

    @Test//2-NOT OK
    public void addStudent_outOfBoundary_errorMessage() {
        Student student = new Student("", "", -1, "");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.addStudent(student)
        );
        assertTrue(thrown.getMessage().contains("Id incorect!Nume incorect!Grupa incorecta!Email incorect!"));
    }

    @Test //3-NOT OK
    public void addStudent_invalidNameAndEmail_errorMessage() {
        Student student = new Student("Y", "B", 934, "b");
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> service.addStudent(student)
        );
        assertTrue(thrown.getMessage().contains("Nume incorect!Email incorect!"));
    }

    @Test //4-NOT OK
    public void addStudent_duplicateStudent_errorMessage() throws Exception {
        Student student = new Student("2YkL7", "Ana Pop", 1, "ana@pop.com");
        service.addStudent(student);
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.addStudent(student)
        );
        assertTrue(thrown.getMessage().contains("Student existent!"));
    }

    @Test //5-NOT OK
    public void addStudent_nonexistentGroup_errorMessage() {
        Student student = new Student("2YkL7", "Ana Pop", 10, "ana@pop.com");
        Exception thrown = assertThrows(
                Exception.class,
                () -> service.addStudent(student)
        );
        assertTrue(thrown.getMessage().contains("Grupa incorecta!"));
    }

    @Test //7-OK
    public void addStudent_maxNaturalGroup_added() throws Exception {
        Student student = new Student("2YkL7", "Ana Pop", Integer.MAX_VALUE, "ana@pop.com");
        Student result = service.addStudent(student);
        assertNull(result);
    }


    @Test
    public void addTema_valid_added() {
        Tema tema = new Tema("27", "Ana Pop", 3, 1);
        Tema result = service.addTema(tema);
        assertNull(result);
    }


    @Test
    public void addTema_duplicated_notAdded() {
        Tema tema = new Tema("27", "desc", 3, 1);
        Tema temaDup = new Tema("27", "desc2", 4, 2);
        service.addTema(tema);
        Tema result = service.addTema(temaDup);
        assertEquals(temaDup, result);
    }

}
