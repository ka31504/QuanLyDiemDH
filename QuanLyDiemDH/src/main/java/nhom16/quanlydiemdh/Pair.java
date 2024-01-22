/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhom16.quanlydiemdh;

/**
 *
 * @author 84816
 */
public class Pair<T, U> {
    private final T first; // Phần tử đầu tiên của cặp
    private final U second; // Phần tử thứ hai của cặp

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}
