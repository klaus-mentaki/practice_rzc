# coding=utf-8
import csv
from random import random

import MySQLdb
import json
import base64
import HTMLParser
import cx_Oracle


class ChengeDBScriptEncode:

    def __init__(self):
        pass

    def getKpiRecord(self, db_host, db_port, db_user, db_pass, db_name):
        import sys
        reload(sys)
        sys.setdefaultencoding('utf8')
        h = HTMLParser.HTMLParser()
        retList = []
        conn = None
        try:
            conn = MySQLdb.connect(host=db_host, port=int(db_port), user=db_user, passwd=db_pass, db=db_name,
                                   charset="utf8")
            cusor = conn.cursor()
            # querySql = "select id,name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name from index_parameter"
            # querySql = "select id,name,chinese_name,detail from index_parameter"
            "获取82构型A320机型的自定义参数名和脚本的对应关系"
            # querySql = "SELECT name,detail from custom_parameter WHERE category='82'"

            "--查询KPI名称对应的测量脚本"
            querySql = "SELECT b.EN_NAME,a.SCRIPT_CONTENT FROM MODEL_SCRIPT a JOIN MODEL_KPI b ON a.PKID = b.SCRIPT_PKID WHERE b.IS_DELETE = 0 AND b.PUBLISH_FLAGS=1;"
            # querySql = "select SCRIPT_CONTENT from MODEL_SCRIPT"
            cusor.execute(querySql)
            results = cusor.fetchall()

            # for result in results:
            #     print result

            for i in results:
                detailJson = i[1]
                detailObject = json.loads(detailJson.encode("utf8"))
                script = detailObject["script"]
                if script == None or len(str(script)) < 20:
                    continue
                try:
                    string = script["string"]
                    temp2 = base64.b64decode(string)
                    temp3 = h.unescape(temp2)
                    script["string"] = base64.b64encode(temp3)
                    kpiCN = script["string"]
                    #detailObject["script"] = script
                    #         retJson = json.dumps(detailObject, sort_keys=True, indent=4, separators=(',', ': '))
                    #         # sql = "insert into index_parameter_bak2 (name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                    #         sql2 = "update index_parameter set name = %s ,organization = %s,chinese_name = %s,description = %s,state = %s,last_update_time = %s,flight_phase = %s,buzz_classification = %s,detail = %s,short_name = %s where id=%s"
                    #         cusor.execute(sql2, [str(i[1]), str(i[2]), str(i[3]), str(i[4]), str(i[5]), str(i[6]), str(i[7]),
                    #                              str(i[8]), str(retJson), str(i[10]), str(i[0])])

                    fileObject = open('25.txt', 'w')
                    for result in results:
                        fileObject.write(i[1]+":"+script["string"])
                        fileObject.write('\n')
                    fileObject.close()

                    conn.commit()
                except Exception, e:
                    print str(e)
        except Exception, e:
            print str(e)
        finally:
            conn.close()
        return ""


    def getAliRecord(self, db_host, db_port, db_user, db_pass, db_name):
        import sys
        reload(sys)
        sys.setdefaultencoding('utf8')
        h = HTMLParser.HTMLParser()
        retList = []
        conn = None
        print 111
        try:
            conn = MySQLdb.connect(host=db_host, port=int(db_port), user=db_user, passwd=db_pass, db=db_name,
                                   charset="utf8")
            cusor = conn.cursor()
            # querySql = "select id,name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name from index_parameter"
            # querySql = "SELECT index_parameter.`name`,index_parameter.detail FROM index_parameter WHERE organization='HO';"
            "自定义参数名字和脚本的对应关系"
            # querySql = "SELECT name,detail from custom_parameter WHERE category='82'"
            # querySql = "select SCRIPT_CONTENT from MODEL_SCRIPT"
            querySql = "SELECT name, detail from custom_parameter"

            cusor.execute(querySql)
            results = cusor.fetchall()

            # for result in results:
            #     print result

            fileObject = open('ALI_Custom.csv', 'w')

            for i in results:
                enmame = i[0]
                #ename = json.loads(enmameJson.encode("utf8"))
                print enmame+":::"+"\n"+"111111111"+"\n"
                fileObject.write(str(enmame)+":"+"\n"+"\n")

                detailJson = i[1]
                detailObject = json.loads(detailJson.encode("utf8"))
                script = detailObject["script"]
                if script == None or len(str(script)) < 20:
                    continue
                try:
                    string = script["string"]
                    temp2 = base64.b64decode(string)
                    temp3 = h.unescape(temp2)
                    #script["string"] = base64.b64encode(temp3)
                    script["string"] = temp3
                    kpiCN = script["string"]
                    print kpiCN
                    fileObject.write(str(kpiCN) + "\n"+ "\n"+ "\n")

                    #print enmame+":"+kpiCN

                    #detailObject["script"] = script
                    #         retJson = json.dumps(detailObject, sort_keys=True, indent=4, separators=(',', ': '))
                    #         # sql = "insert into index_parameter_bak2 (name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                    #         sql2 = "update index_parameter set name = %s ,organization = %s,chinese_name = %s,description = %s,state = %s,last_update_time = %s,flight_phase = %s,buzz_classification = %s,detail = %s,short_name = %s where id=%s"
                    #         cusor.execute(sql2, [str(i[1]), str(i[2]), str(i[3]), str(i[4]), str(i[5]), str(i[6]), str(i[7]),
                    #                              str(i[8]), str(retJson), str(i[10]), str(i[0])])




                    # for i in range(results):
                    #     print random.randint(0,results)
                    #     for i in range(results): fileObject.write(str(enmame+":"+kpiCN))
                    #     fileObject.write('\n')

                    conn.commit()

                except Exception, e:
                    print str(e)

                # for result in results:
                #     fileObject.write(str(enmame+":"+kpiCN))
                #     fileObject.write('\n')
                #     fileObject.flush()
                #     # fileObject.close()
        except Exception,e:
            print str(e)
        finally:
            conn.close()
        return ""



    def getOraRecord(self, db_host, db_port, db_user, db_pass, db_name):
        import sys

        reload(sys)
        sys.setdefaultencoding('utf8')
        h = HTMLParser.HTMLParser()
        retList = []
        conn = None
        try:
            conn = MySQLdb.connect(host=db_host, port=int(db_port), user=db_user, passwd=db_pass, db=db_name,
                                   charset="utf8")
            cusor = conn.cursor()
            # querySql = "select id,name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name from index_parameter"
            querySql = "SELECT * FROM MODEL_SCRIPT "
            # querySql = "select SCRIPT_CONTENT from MODEL_SCRIPT"
            cusor.execute(querySql)
            results = cusor.fetchall()

            # tns = cx_Oracle.makedsn(host=db_host, port=int(db_port), db=db_name)
            # db = cx_Oracle.connect(user=db_user, passwd=db_pass, tns)  # 创建连接
            # cr = db.cursor()

            for result in results:
                print result

            # for i in results:
            #     detailJson = i[3]
            #     detailObject = json.loads(detailJson.encode("utf8"))
            #     script = detailObject["script"]
            #     if script == None or len(str(script)) < 20:
            #         continue
            #     try:
            #         string = script["string"]
            #         temp2 = base64.b64decode(string)
            #         temp3 = h.unescape(temp2)
            #         script["string"] = base64.b64encode(temp3)
            #         kpiCN = script["string"]
            #         #detailObject["script"] = script
            #         #         retJson = json.dumps(detailObject, sort_keys=True, indent=4, separators=(',', ': '))
            #         #         # sql = "insert into index_parameter_bak2 (name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
            #         #         sql2 = "update index_parameter set name = %s ,organization = %s,chinese_name = %s,description = %s,state = %s,last_update_time = %s,flight_phase = %s,buzz_classification = %s,detail = %s,short_name = %s where id=%s"
            #         #         cusor.execute(sql2, [str(i[1]), str(i[2]), str(i[3]), str(i[4]), str(i[5]), str(i[6]), str(i[7]),
            #         #                              str(i[8]), str(retJson), str(i[10]), str(i[0])])
            #
            #         fileObject = open('25.txt', 'w')
            #         for result in results:
            #             fileObject.write(i[1]+":"+script["string"])
            #             fileObject.write('\n')
            #         fileObject.close()
            #
            #         conn.commit()
            #     except Exception, e:
            #         print str(e)

        finally:
            conn.close()
        return ""


    def getOraRecord1(self):
        import sys
        import cx_Oracle

        reload(sys)
        sys.setdefaultencoding('utf8')
        tns = cx_Oracle.makedsn('172.16.10.26', '1521', 'ho_test')
        db = cx_Oracle.connect('ho_test', '123456', tns)
        print tns
        print db.version
        vs = db.version.split('.')

    def a(self):
        import sys
        import cx_Oracle

        reload(sys)
        sys.setdefaultencoding('utf8')

        dsnStr = cx_Oracle.makedsn('172.16.10.26', '1521', 'ho_test')

        print 111
        #print(dsnStr.version)
        sql = "SELECT * FROM MODEL_SCRIPT"
        conn = cx_Oracle.connect(user="ho_test", password="123456", dsn=dsnStr)
        try:
            cursor = conn.cursor()
            cursor.execute(sql)

            rs = cursor.fetchall()


            for x in rs:
                print x
            print "This is Fetone!\n"

            cursor.close()
            conn.close()
        except Exception, e:
            print e

    def getEventRecord(self, db_host, db_port, db_user, db_pass, db_name):
        import sys
        reload(sys)
        sys.setdefaultencoding('utf8')
        h = HTMLParser.HTMLParser()
        retList = []
        conn = None
        try:
            conn = MySQLdb.connect(host=db_host, port=int(db_port), user=db_user, passwd=db_pass, db=db_name,
                                   charset="utf8")
            cusor = conn.cursor()
            querySql = "select id,name,organization,chinese_name,description,state,last_update_time,flight_phase,detail from time_point_parameter"
            cusor.execute(querySql)
            results = cusor.fetchall()

            for i in results:
                detailJson = i[8]
                try:
                    detailObject = json.loads(detailJson.encode("utf8"))
                except Exception, e:
                    print str(e)
                script = detailObject["script"]
                if script == None or len(str(script)) < 20:
                    continue
                try:
                    string = script["string"]
                    temp2 = base64.b64decode(string)
                    temp3 = h.unescape(temp2)
                    script["string"] = base64.b64encode(temp3)
                    detailObject["script"] = script
                    retJson = json.dumps(detailObject, sort_keys=True, indent=4, separators=(',', ': '))
                    # sql = "insert into index_parameter_bak2 (name,organization,chinese_name,description,state,last_update_time,flight_phase,buzz_classification,detail,short_name) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
                    sql2 = "update time_point_parameter set name = %s ,organization = %s,chinese_name = %s,description = %s,state = %s,last_update_time = %s,flight_phase = %s,detail = %s where id=%s"
                    cusor.execute(sql2, [str(i[1]), str(i[2]), str(i[3]), str(i[4]), str(i[5]), str(i[6]), str(i[7]),
                                         str(retJson), str(i[0])])
                    conn.commit()
                except Exception, e:
                    print str(e)

        finally:
            conn.close()
        return ""


if __name__ == '__main__':
    # t = ChengeDBScriptEncode()
    # t.getKpiRecord("172.16.10.25", "3306", "root", "123456", "aero_test")

    #t = ChengeDBScriptEncode()
    #t.getKpiRecord("172.16.10.25", "3306", "root", "123456", "aero_test")

    # t = ChengeDBScriptEncode()
    # t.getEventRecord("172.16.10.26", "1521", "root", "123456", "HO_TEST")

    t = ChengeDBScriptEncode()
    t.getAliRecord("47.94.204.25", "3306", "root", "123456", "aero_test")

    #t = ChengeDBScriptEncode()
    #t.getOraRecord1()

    # t = ChengeDBScriptEncode()
    # t.getOraRecord1()