package validation;

import domain.Groups;
import domain.Student;

public class StudentValidator implements Validator<Student> {

    /**
     * Valideaza un student
     *
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        //CHANGED
        StringBuilder errors = new StringBuilder();
        Groups groups = new Groups();

        if (entity.getID().equals("")) {
            errors.append("Id incorect!");
        } else if (entity.getID() == null) {
            errors.append("Id incorect!");
        }
        if (entity.getNume().equals("")) {
            errors.append("Nume incorect!");
        } else {
            if (!entity.getNume().contains(" ")) {
                errors.append("Nume incorect!");
            } else if (entity.getNume() == null) {
                errors.append("Nume incorect!");
            }
        }
        if (entity.getGrupa() < 0) {
            errors.append("Grupa incorecta!");
        } else {
            if (!groups.exists(entity.getGrupa())) {
                errors.append("Grupa incorecta!");
            }
        }
        if (entity.getEmail() == null) {
            errors.append("Email incorect!");
        } else {
            if (entity.getEmail().equals("")) {
                errors.append("Email incorect!");
            } else if (!entity.getEmail().contains("@") || !entity.getEmail().contains(".")) {
                errors.append("Email incorect!");
            }
        }
        if (errors.length() != 0) {
            throw new ValidationException(errors.toString());
        }
    }
}
