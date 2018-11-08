ARG APAMA_VERSION=10.3
ARG APAMA_BUILDER=store/softwareag/apama-builder:${APAMA_VERSION}
ARG APAMA_IMAGE=store/softwareag/apama-correlator:${APAMA_VERSION}

# Use the build environment
FROM ${APAMA_BUILDER} as builder

#setup the source
COPY --chown=1724:1724 src ${APAMA_WORK}/src
COPY --chown=1724:1724 build.xml ${APAMA_WORK}/build.xml
COPY --chown=1724:1724 complex.xml ${APAMA_WORK}/complex.xml

#build the application
RUN ant

#here we will test the application using pysys
#RUN pysys run 

FROM ${APAMA_IMAGE}
COPY --from=builder ${APAMA_WORK}/complex.jar ${APAMA_WORK}/complex.jar
COPY --from=builder ${APAMA_WORK}/complex.yaml ${APAMA_WORK}/complex.yaml

CMD ["correlator", "--config", "dos.yaml"]