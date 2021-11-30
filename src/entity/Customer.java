package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer")
public class Customer {
    @Id
    private String customerID;
    private String name;
    private String address;
    private String nic;
    private String contact;
    private String email;

    public Customer(String customerID, String name, String address, String nic, String contact, String email) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.nic = nic;
        this.contact = contact;
        this.email = email;
    }

    public Customer() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nic='" + nic + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
