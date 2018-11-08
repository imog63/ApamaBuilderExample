ARG APAMA_VERSION=10.3
ARG APAMA_BUILDER=store/softwareag/apama-builder:${APAMA_VERSION}
ARG APAMA_IMAGE=store/softwareag/apama-correlator:${APAMA_VERSION}

# Use the build environment
FROM ${APAMA_BUILDER} as builder

#setup the source
COPY src ${APAMA_WORK}/src
COPY build.xml ${APAMA_WORK}/build.xml

#build the application
RUN ant

#here we will test the application using pysys
#RUN pysys run 

FROM ${APAMA_IMAGE}
COPY --from=builder ${APAMA_WORK}/dos-jmon.jar ${APAMA_WORK}/dos-jmon.jar

CMD ["correlator", "--config", "dos.yaml"]