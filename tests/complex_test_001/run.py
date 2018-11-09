# Sample PySys testcase
# Copyright (c) 2015-2018 Software AG, Darmstadt, Germany and/or Software AG USA Inc., Reston, VA, USA, and/or its subsidiaries and/or its affiliates and/or their licensors. 
# Use, reproduction, transfer, publication or disclosure is prohibited except as specifically provided for in your License Agreement with Software AG 
import os

from pysys.constants import *
from apama.basetest import ApamaBaseTest
from apama.correlator import CorrelatorHelper

class PySysTest(ApamaBaseTest):

	def execute(self):
		# create the correlator helper, start the correlator and attach an 
		# engine_receive process listening to a test channel. The helper will 
		# automatically get an available port that will be used for all 
		# operations against it
		correlator = CorrelatorHelper(self, name='testcorrelator')
		correlator.start(logfile='testcorrelator.log',config=[os.path.join(self.project.APAMA_WORK,'complex.yaml')])
		correlator.applicationEventLogging(enable=True)
		
		# inject the monitor (directory defaults to the testcase input)
		correlator.injectEPL(filenames=[os.path.join(self.project.APAMA_WORK,'ComplexPlugin.mon')])
		
		# wait for all events to be processed
		correlator.flush()
		
		
	def validate(self):
		# look for log statements in the correlator log file
		self.assertGrep('testcorrelator.log', expr=' (ERROR|FATAL) ', contains=False)
		
