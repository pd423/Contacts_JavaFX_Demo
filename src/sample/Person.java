package sample;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import sample.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Person {

    private final StringProperty mFirstName;
    private final StringProperty mLastName;
    private final StringProperty mStreet;
    private final IntegerProperty mPostalCode;
    private final StringProperty mCity;
    private final ObjectProperty<LocalDate> mBirthday;

    public Person() {
        this(null, null);
    }

    public Person(String firstName, String lastName) {
        mFirstName = new SimpleStringProperty(firstName);
        mLastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        mStreet = new SimpleStringProperty("ZhongYangBei Rd");
        mPostalCode = new SimpleIntegerProperty(112);
        mCity = new SimpleStringProperty("Taipei");
        mBirthday = new SimpleObjectProperty<>(LocalDate.of(1990, 12, 25));
    }

    public String getFirstName() {
        return mFirstName.get();
    }

    public void setFirstName(String firstName) {
        mFirstName.set(firstName);
    }

    public StringProperty getFirstNameProperty() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName.get();
    }

    public void setLastName(String lastName) {
        mLastName.set(lastName);
    }

    public StringProperty getLastNameProperty() {
        return mLastName;
    }

    public String getStreet() {
        return mStreet.get();
    }

    public void setStreet(String street) {
        mStreet.set(street);
    }

    public StringProperty getStreetProperty() {
        return mStreet;
    }

    public int getPostalCode() {
        return mPostalCode.get();
    }

    public void setPostalCode(int postalCode) {
        mPostalCode.set(postalCode);
    }

    public IntegerProperty getPostalCodeProperty() {
        return mPostalCode;
    }

    public String getCity() {
        return mCity.get();
    }

    public void setCity(String city) {
        mCity.set(city);
    }

    public StringProperty getCityProperty() {
        return mCity;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return mBirthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        mBirthday.set(birthday);
    }

    public ObjectProperty<LocalDate> getBirthdayProperty() {
        return mBirthday;
    }
}
