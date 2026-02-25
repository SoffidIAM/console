//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB PrinterService
 */
public interface PrinterService

 {

	com.soffid.iam.iga.api.Printer create(
		final com.soffid.iam.iga.api.Printer impressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Printer findPrinterByPrinterName(
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Printer update(
		final com.soffid.iam.iga.api.Printer impressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterGroup create(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterGroup findPrinterGroupByGroupNameAndPrinterName(
		final java.lang.String codiGrup, 
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterGroup update(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterUser create(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterUser findPrinterUserByUserNameAndPrinterName(
		final java.lang.String codiUsuari, 
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.PrinterUser update(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> findPrinterUsers(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> findPrinters(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> findPrintersGroupByGroupName(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByPrinterName(
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByFilter(
		final java.lang.String codi, 
		final java.lang.String model, 
		final java.lang.String local, 
		final java.lang.String maquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection getPrintersGroupByPrinterName(
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Printer> getPrinters()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.PrinterUser> getUserPrintersByPrinterName(
		final java.lang.String codiImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.Printer impressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
