ARG APAMA_VERSION=10.3
ARG APAMA_BUILDER=store/softwareag/apama-builder:${APAMA_VERSION}
ARG APAMA_IMAGE=store/softwareag/apama-correlator:${APAMA_VERSION}

# Use the build environment
FROM ${APAMA_BUILDER} as builder

#setup the source
COPY --chown=1724:1724 ComplexPlugin.java ${APAMA_WORK}/ComplexPlugin.java
COPY --chown=1724:1724 ComplexPlugin.mon ${APAMA_WORK}/ComplexPlugin.mon
COPY --chown=1724:1724 ComplexPlugin.xml ${APAMA_WORK}/ComplexPlugin.xml
COPY --chown=1724:1724 ComplexPluginSample.txt ${APAMA_WORK}/ComplexPluginSample.txt
COPY --chown=1724:1724 build.xml ${APAMA_WORK}/build.xml
COPY --chown=1724:1724 complex.yaml ${APAMA_WORK}/complex.yaml

#build the application
RUN ant

#here we will test the application using pysys
#RUN pysys run 

FROM ${APAMA_IMAGE}
COPY --from=builder ${APAMA_WORK}/complex_plugin.jar ${APAMA_WORK}/complex_plugin.jar
COPY --from=builder ${APAMA_WORK}/complex.yaml ${APAMA_WORK}/complex.yaml

CMD ["correlator", "--config", "complex.yaml"]
