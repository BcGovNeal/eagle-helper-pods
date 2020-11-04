FROM image-registry.openshift-image-registry.svc:5000/6cdc9e-tools/jenkins-basic:stable
USER 0
COPY ./contrib/jenkins/configuration $JENKINS_REF_HOME

ARG NODE_VERSION=v15.0.1

RUN set -x && \
    chgrp -R 0 $JENKINS_REF_HOME && \
    chmod -R 644 $JENKINS_REF_HOME && \
    chmod -R g+rwX $JENKINS_REF_HOME && \
    yum install -y python3 gcc-c++ make

RUN fix_permission() { while [[ $# > 0 ]] ; do chgrp -R 0 "$1" && chmod -R g=u "$1"; shift; done } && \
    set -x && \
    curl -sSL -o /tmp/node-${NODE_VERSION}-linux-x64.tar.gz https://nodejs.org/dist/${NODE_VERSION}/node-${NODE_VERSION}-linux-x64.tar.gz && \
    tar -xf /tmp/node-${NODE_VERSION}-linux-x64.tar.gz -C /tmp && \
    mv /tmp/node-${NODE_VERSION}-linux-x64 /opt/node && \
    fix_permission '/opt/node'

ENV NODE_HOME=/opt/node \
    PATH=$PATH:/opt/node/bin

USER 1001
