package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 25;
        short s = 250;
        char c = 506;
        int i = 1000;
        long l = 100000;
        float f = 3.14f;
        double d = 4.25;
        boolean bool = true;

        LOG.debug("byte = {}, short = {}, char = {}, int = {}"
                        + ", long = {}, float = {}, double = {}, boolean = {}",
                        b, s, c, i, l, f, d, bool);

    }
}
