//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service PrinterService
 */
public interface PrinterService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.PrinterService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.PrinterService";

	/**
	 * Operation create

	 * @param impressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.Printer create(
		final com.soffid.iam.iga.api.Printer impressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrinterByPrinterName

	 * @param codiImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.Printer findPrinterByPrinterName(
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param impressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.Printer update(
		final com.soffid.iam.iga.api.Printer impressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param grupImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterGroup create(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrinterGroupByGroupNameAndPrinterName

	 * @param codiGrup 
	 * @param codiImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterGroup findPrinterGroupByGroupNameAndPrinterName(
		final java.lang.String codiGrup, 
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param grupImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterGroup update(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param usuariImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterUser create(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrinterUserByUserNameAndPrinterName

	 * @param codiUsuari 
	 * @param codiImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterUser findPrinterUserByUserNameAndPrinterName(
		final java.lang.String codiUsuari, 
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param usuariImpressora 
	 * @return 
	 */
	com.soffid.iam.iga.api.PrinterUser update(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrinterUsers

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> findPrinterUsers(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrinters

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> findPrinters(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrintersGroupByGroupName

	 * @param codiGrup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> findPrintersGroupByGroupName(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrintersByPrinterName

	 * @param codiImpressora 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByPrinterName(
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrintersByFilter

	 * @param codi 
	 * @param model 
	 * @param local 
	 * @param maquina 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByFilter(
		final java.lang.String codi, 
		final java.lang.String model, 
		final java.lang.String local, 
		final java.lang.String maquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPrintersGroupByPrinterName

	 * @param codiImpressora 
	 * @return 
	 */
	java.util.Collection getPrintersGroupByPrinterName(
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPrinters

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Printer> getPrinters()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserPrintersByPrinterName

	 * @param codiImpressora 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.PrinterUser> getUserPrintersByPrinterName(
		final java.lang.String codiImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param impressora 
	 */
	void delete(
		final com.soffid.iam.iga.api.Printer impressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param grupImpressora 
	 */
	void delete(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param usuariImpressora 
	 */
	void delete(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
