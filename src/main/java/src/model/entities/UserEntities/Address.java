package src.model.entities.UserEntities;

public final class Address {

    private String cep;

    private String number;

    private String complement;

    private String label;

    private String city;
    
    public Address () {}

    public Address (String cep, String label, String complement, String number, String city) {

        this.cep = cep;

        this.label = label;

        this.complement = complement;

        this.number = number;

        this.city = city;

    }

    public Address (String CEP, String label) {

        this.cep = CEP;

        this.label = label;

    }

    public String getCEP () {

        return cep;

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
                "CEP='" + cep + '\'' +
                ", number='" + number + '\'' +
                ", complement='" + complement + '\'' +
                ", neighbourhood='" + label + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
