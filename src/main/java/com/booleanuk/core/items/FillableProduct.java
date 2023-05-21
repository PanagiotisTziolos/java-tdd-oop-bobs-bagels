package com.booleanuk.core.items;

import com.booleanuk.core.format.FirstLetterToUpperFormat;
import com.booleanuk.core.format.Format;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class FillableProduct<T extends Filling> implements FillableItem<T> {
    private final String variant;
    private final double price;
    private final Format format;
    private final List<T> fillings;

    public FillableProduct(String variant, double price) {
        this(variant, price, new FirstLetterToUpperFormat(), new ArrayList<>());
    }

    public FillableProduct(String variant, double price, Format format, List<T> fillings) {
        this.variant = variant;
        this.price = price;
        this.format = format;
        this.fillings = fillings;
    }

    @Override
    public String variant() {
        return Arrays.stream(this.variant.split(" "))
                .reduce("", (sum, s) -> sum + format.text(s) + " ",String::concat).trim();
    }

    @Override
    public double cost() {
        return this.price + fillings.stream()
                .reduce(0.0, (sum, i) -> sum + i.cost(), Double::sum);
    }

    @Override
    public void add(T filling) {
        this.fillings.add(filling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.variant(), this.cost());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof FillableProduct<?> objBagel)) return false;

        List<Item> thisFillings = fillings.stream().sorted(Comparator.comparing(Item::variant)).collect(Collectors.toList());
        List<Item> objBagelFillings = objBagel.fillings.stream().sorted(Comparator.comparing(Item::variant)).collect(Collectors.toList());

        return this.variant().equals(objBagel.variant()) &&
                objBagel.fillings.size() == this.fillings.size() &&
                IntStream.range(0, thisFillings.size()).filter(i -> !thisFillings.get(i).equals(objBagelFillings.get(i))).findFirst().isEmpty();
    }
}