package programmermuda.mvc.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CreatePersonRequest {

    private List<String> hobbies;

    private List<CreateSocialMediaRequest> socialMedias;

    private CreateAddressRequest address;

    @NotBlank
    private String firstName;

    private String middleName;

    private String lastName;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    public CreatePersonRequest(List<String> hobbies, List<CreateSocialMediaRequest> socialMedias, CreateAddressRequest address, String firstName, String middleName, String lastName, String email, String phone) {
        this.hobbies = hobbies;
        this.socialMedias = socialMedias;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public CreatePersonRequest() {
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public List<CreateSocialMediaRequest> getSocialMedias() {
        return socialMedias;
    }

    public void setSocialMedias(List<CreateSocialMediaRequest> socialMedias) {
        this.socialMedias = socialMedias;
    }

    public CreateAddressRequest getAddress() {
        return address;
    }

    public void setAddress(CreateAddressRequest address) {
        this.address = address;
    }





    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
