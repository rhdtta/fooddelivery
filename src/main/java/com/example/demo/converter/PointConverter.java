package com.example.demo.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.locationtech.jts.geom.Point;
import org.postgresql.util.PGobject;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;

@Converter(autoApply = true)
public class PointConverter implements AttributeConverter<Point, PGobject> {

    private final WKTWriter wktWriter = new WKTWriter();
    private final WKTReader wktReader = new WKTReader();

    @Override
    public PGobject convertToDatabaseColumn(Point attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            PGobject pgObject = new PGobject();
            pgObject.setType("geometry");
            pgObject.setValue(wktWriter.write(attribute));
            return pgObject;
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert Point to PGobject", e);
        }
    }

    @Override
    public Point convertToEntityAttribute(PGobject dbData) {
        if (dbData == null || dbData.getValue() == null) {
            return null;
        }
        try {
            return (Point) wktReader.read(dbData.getValue());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert PGobject to Point", e);
        }
    }
}


