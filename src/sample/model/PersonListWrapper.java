package sample.model;

import sample.Person;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Helper class to wrap a list of persons. This is used for saving the list of persons to XML.
 *
 * @author Marco Jakob
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Person> mPersonList;

    @XmlElement(name = "person")
    public List<Person> getPersonList() {
        return mPersonList;
    }

    public void setPersonList(List<Person> personList) {
        mPersonList = personList;
    }
}
