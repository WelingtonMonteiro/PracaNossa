package core

class Shows {

    Date data
    String local
    Integer quantidadePessoas
    String nome

    static hasMany = [humoristas: Humorista] //tem muitos
    static belongsTo = [Humorista] //pertence a

    static constraints = {
        quantidadePessoas min: 1
        data min: new Date()
    }
}
