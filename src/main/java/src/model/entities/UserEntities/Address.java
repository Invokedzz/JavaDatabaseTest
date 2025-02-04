package src.model.entities.UserEntities;

public final class Address {

    private String CEP;

    private String number;

    private String complement;

    private String label;

    private String city;
    
    public Address () {}

    public Address (String CEP, String label, String complement, String number, String city) {

        this.CEP = CEP;

        this.label = label;

        this.complement = complement;

        this.number = number;

        this.city = city;

    }


    public String getCEP () {

        return CEP;

    }

    public String getCity () {

        return city;

    }

    public String getLabel () {

        return label;

    }

    public String getComplement () {

        return complement;

    }

    public String getNumber () {

        return number;

    }

    @Override
    public String toString() {
        return "Address{" +
                "CEP='" + CEP + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", neighbourhood='" + label + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
