package fr.huskago.objects;

// Création d'un tuple générique
public class Tuple<T1, T2> {
    // Déclaration des variables
    private T1 item1;
    private T2 item2;

    // Constructeur
    public Tuple(T1 item1, T2 item2) {
        this.item1 = item1;
        this.item2 = item2;
    }

    // Getters et setters
    public T1 getItem1() { return this.item1; }

    public T2 getItem2() { return this.item2; }

    public void setItem1(T1 item1) { this.item1 = item1; }

    public void setItem2(T2 item2) { this.item2 = item2; }
}
