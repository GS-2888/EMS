package com.ems.server;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

public class JasperReportCompilationExample {
   public static void main(String[] args) {
      String sourceFileName = "C://checkout/payslip.jrxml";
      System.out.println("Compiling Report Design ...");
      try {
       String jasperReport =  JasperCompileManager.compileReportToFile(sourceFileName);
      } catch (JRException e) {
         e.printStackTrace();
      }
      System.out.println("Compilation Done!!! ...");
   }
}