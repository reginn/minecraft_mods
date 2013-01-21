
import csv
import glob
import os
import re
import shutil
import itertools

getchangedsrc   = 'getchangedsrc.bat'
basePackageName = 'rgn/mods/'
methodFilePath  = 'conf/methods.csv'
fieldFilePath   = 'conf/fields.csv'

class wordReplace():  
	def __init__(self, **keywords):
		self.adict = dict(**keywords)  
		self.rx = self.make_rx()  
	
	def make_rx(self):
		return re.compile(r'\b%s\b' % r'\b|\b'.join(map(re.escape, self.adict)))   
	
	def rename(self, match):
		try:
			return self.adict[match.group(0)]
		except KeyError:
			pass
		return match.group(0)
		
	def __call__(self, text):
		return self.rx.sub(self.rename, text)

def buildModList():

	fileList = os.listdir("./modsrc/minecraft/" + basePackageName)

	return fileList

def inverse(modName):
	
	srcPathList = glob.glob('./modsrc/minecraft/' + basePackageName + modName + '/*.java')
	
	for srcPath in srcPathList:
		doInverse(srcPath)

def readCsv(fileName):
	
	file = None
	dict = {'first':{}, 'second':{}, 'third':{}, 'fourth':{}}
	list = []
	
	try:
		file = open(fileName, 'rb')
		for row in csv.DictReader(file):
			list.append(row)
		
	finally:
		file.close()

	size = len(list)
	
	for i in xrange(size/4):
		dict['first'][list[i]['name']] = list[i]['searge']
	
	for i in xrange(size/4, 2 * size/4):
		dict['second'][list[i]['name']] = list[i]['searge']
	
	for i in xrange(2 * size/4, 3*size/4):
		dict['third'][list[i]['name']] = list[i]['searge']

	for i in xrange(3 * size/4, 4*size/4):
		dict['fourth'][list[i]['name']] = list[i]['searge']
	
	return dict
		
def doInverse(src):

	src_file = None
	tmp_file = None

	try:
		src_file = open(src, 'rb')
		tmp_file = open(src + '.tmp', 'wb')

		for src_line in src_file:
			
			src_line = method_first(src_line)
			src_line = method_second(src_line)
			src_line = method_thrid(src_line)
			src_line = method_fourth(src_line)
			
			src_line = field_first(src_line)
			src_line = field_second(src_line)
			src_line = field_thrid(src_line)
			src_line = field_fourth(src_line)
			
			tmp_file.write(src_line)

	finally:
		src_file.close()
		tmp_file.close()
		
	shutil.move(src + '.tmp', src)
	
#----------------------------------------------------------- main
if __name__ == "__main__":

	if os.path.exists("./modsrc"):
		shutil.rmtree("./modsrc")
	
	os.system(getchangedsrc)
	
	print "inverse updatenames..."
	
	modList = buildModList()
		
	print "MOD LIST"
	for modNames in modList:
		print modNames
	
	methods = readCsv(methodFilePath)
	fields  = readCsv(fieldFilePath)
	
	method_first  = wordReplace(**methods['first'])
	method_second = wordReplace(**methods['second'])
	method_thrid  = wordReplace(**methods['third'])
	method_fourth = wordReplace(**methods['fourth'])
	
	field_first  = wordReplace(**fields['first'])
	field_second = wordReplace(**fields['second'])
	field_thrid  = wordReplace(**fields['third'])
	field_fourth = wordReplace(**fields['fourth'])
	
	for modName in modList:
		inverse(modName)
	
	print "\ninverse updatenames completed"
	