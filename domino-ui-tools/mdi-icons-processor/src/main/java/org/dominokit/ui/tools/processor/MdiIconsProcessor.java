
package org.dominokit.ui.tools.processor;

import com.google.auto.service.AutoService;
import org.dominokit.domino.apt.commons.BaseProcessor;
import org.dominokit.domino.ui.MdiIconsFactory;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@AutoService(Processor.class)
public class MdiIconsProcessor extends BaseProcessor {

    private final Set<String> supportedAnnotations = new HashSet<>();

    public MdiIconsProcessor() {
        supportedAnnotations.add(MdiIconsFactory.class.getCanonicalName());
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return supportedAnnotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        new MdiIconsProcessingStep.Builder()
                .setProcessingEnv(processingEnv)
                .build()
                .process(roundEnv.getElementsAnnotatedWith(MdiIconsFactory.class));
        return false;
    }

}
