package src.model.entities.UserEntities;

public final class Address {

    private String CEP; // user can't alter this information

    private Integer number;

    private String complement;

    private String neighbourhood;

    private String city;

    public Address () {}

    public Address (String CEP, Integer number, String complement, String neighbourhood, String city) {

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

    public Integer getNumber () {

        return number;

    }

}
