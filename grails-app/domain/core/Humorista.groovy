package core

class Humorista {

    String nome
    String nomeArtistico
    Integer nivel
    Integer processos
    Date DataNascimento
    Double Salario

    static hasMany = [piadas: Piada, shows: Shows] // tem muitos

    static mapping = {
        shows cascade: 'all-delete-orphan'
        table name: 'humoristas'
        id column: 'humorista_id'
    }

    static constraints = {
        salario min:0d
        dataNascimento nullable: true
    }
}
