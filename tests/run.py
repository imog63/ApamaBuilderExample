from pysys.constants import *
from pysys.basetest import BaseTest
from apama.docker.framework import DockerImage, DockerSecret, DockerService
import shutil, time, binascii, os, subprocess

class PySysTest(BaseTest):
	def execute(self):
		APAMA_HOME = os.getenv('APAMA_HOME', '')
		samples = os.getenv('APAMA_SAMPLES', '%s/samples' % APAMA_HOME)

		self.sampleDir = samples+"/docker/applications/Secrets"

		if PROJECT.CORRELATOR_IMAGE:
			img = DockerImage.fromExisting(self, PROJECT.CORRELATOR_IMAGE)
		else:
			img = DockerImage.fromDockerfile(self, file=os.path.join(PROJECT.DOCKER_PRODUCT_HOME, "image/Dockerfile"), context=PROJECT.APAMA_SAG_HOME)
		
		app = DockerImage.fromDockerfile(self, file=self.sampleDir+'/Dockerfile', context=self.sampleDir, buildArgs=['APAMA_IMAGE='+img.getName()])
		
		secret = DockerSecret.fromContent(self, secretContents='CORRELATOR_NAME=MyCorrelatorName', name=DockerImage.generateUniqueName()+".properties")

		service = DockerService.fromImage(app)

		service.run(extraArgs=["--secret="+secret.getName()])
		service.log('correlator.log')

		self.waitForSignal(file="correlator.log.out", expr="Component ID:")

	def validate(self):
		self.assertGrep(file="correlator.log.out", expr="Component ID: MyCorrelatorName")
