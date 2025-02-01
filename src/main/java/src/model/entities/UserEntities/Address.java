package src.model.entities.UserEntities;

public final class Address {

    private String CEP; // user can't alter this information

    private String number;

    private String complement;

    private String neighbourhood;

    private String city;

    public Address () {}

    public Address (String CEP, String number, String complement, String neighbourhood, String city) {

        this.CEP = CEP;

        this.number = number;

        this.complement = complement;

        this.neighbourhood = neighbourhood;

        this.city = city;

    }

    public String getCEP () {

        return CEP;

    }

    public String getCity () {

        return city;

    }

    public String getNeighbourhood () {

        return neighbourhood;

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
                ", neighbourhood='" + neighbourhood + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
