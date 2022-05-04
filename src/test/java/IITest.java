import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IITest {

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
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void t1_addStudent() throws Exception {
        Student student = new Student("2778", "Manuela Mah", 934, "man@yahoo.com");
        Student result = service.addStudent(student);
        assertNull(result);
    }

    @Test()
    public void t2_addTema() throws Exception {
        Tema tema = new Tema("27", "Ana Pop", 3, 1);
        Tema result = service.addTema(tema);
        assertNull(result);
    }

    @Test
    public void t3_all() throws Exception {
        Tema tema = new Tema("27", "Ana Pop", 3, 1);
        service.addTema(tema);
        Student student = new Student("2778", "Manuela Mah", 934, "man@yahoo.com");
        service.addStudent(student);
        Nota nota = new Nota("185", "2778", "27", 10, LocalDate.now());
        double result = service.addNota(nota, "feedback");
        assertTrue(result == 1.0d);
    }

}
