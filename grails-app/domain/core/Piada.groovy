package core

class Piada {
    String texto
    Integer nivelGraca
    Humorista humorista

    static belongsTo = [Humorista]

    static constraints = {
        nivelGraca min: 0
    }
}
