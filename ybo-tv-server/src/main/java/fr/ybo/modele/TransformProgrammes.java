package fr.ybo.modele;

import com.google.common.base.Function;
import fr.ybo.xmltv.Programme;

import java.io.Serializable;

public class TransformProgrammes implements Function<Programme, ProgrammeForMemCache>, Serializable {
    @Override
    public ProgrammeForMemCache apply(Programme programme) {
        return ProgrammeForMemCache.fromProgramme(programme);
    }
}
