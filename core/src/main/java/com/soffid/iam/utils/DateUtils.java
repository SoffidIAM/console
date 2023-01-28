package com.soffid.iam.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import es.caib.seycon.ng.exception.InternalErrorException;

public class DateUtils {

	public static Date nullDate = new Date(0);

	private static DateQuery getDateQueryFromQuery(String dateQuery) throws InternalErrorException {
		String dateQueryClean;
		boolean secondsPrecision = false;
		Date date = null;
		if (dateQuery.startsWith("<") || dateQuery.startsWith(">") //$NON-NLS-1$ //$NON-NLS-2$
				|| dateQuery.startsWith("=")) { //$NON-NLS-1$
			dateQueryClean = dateQuery.substring(1).trim();
		} else {
			dateQueryClean = dateQuery.trim();
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
			date = dateFormat.parse(dateQueryClean);
			secondsPrecision = true;
		} catch (Exception e) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"dd/MM/yyyy kk:mm"); //$NON-NLS-1$
				date = dateFormat.parse(dateQueryClean);
				secondsPrecision = true;
			} catch (Exception e2) {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
					date = dateFormat.parse(dateQueryClean);
					secondsPrecision = false;
				} catch (Exception ex) {
					throw new es.caib.seycon.ng.exception.InternalErrorException(
							String.format(Messages.getString("DateUtils.IncorrectFullDate"), //$NON-NLS-1$
									dateQueryClean, e.getMessage()));
				}
			}
		}
		return new DateQuery(dateQuery, date, secondsPrecision);
	}

	public static LimitDates getLimitDatesFromQuery(String query) throws InternalErrorException {
		if (query.indexOf("-") != -1) { //$NON-NLS-1$
			String[] queries = query.split("-"); //$NON-NLS-1$
			if (queries.length != 2) {
				throw new es.caib.seycon.ng.exception.InternalErrorException(
						String.format(Messages.getString("DateUtils.IncorrectDate")));  //$NON-NLS-1$
			}
			String maxQuery = queries[1];
			maxQuery = maxQuery.trim();
			String minQuery = queries[0];
			minQuery = minQuery.trim();
			DateQuery maxDateQuery = getDateQueryFromQuery(maxQuery);
			DateQuery minDateQuery = getDateQueryFromQuery(minQuery);
			LimitDates maxLimitDate = getLimitDatesFromDateQuery(maxDateQuery);
			LimitDates minLimitDate = getLimitDatesFromDateQuery(minDateQuery);
			return new LimitDates(maxLimitDate.getMaximum(), minLimitDate
					.getMinimum());
		} else {
			DateQuery dateQuery = getDateQueryFromQuery(query);
			return getLimitDatesFromDateQuery(dateQuery);
		}
	}

	private static LimitDates getLimitDatesFromDateQuery(DateQuery dateQuery) {
		String query = dateQuery.getQuery();
		Date date = dateQuery.getDate();
		boolean secondsPrecision = dateQuery.isSecondsPrecision();
		Date dateMin = DateUtils.nullDate;
		Date dateMax = DateUtils.nullDate;
		if (query.startsWith("<") || query.startsWith(">")) { //$NON-NLS-1$ //$NON-NLS-2$
			char searchFilter = query.charAt(0);
			switch (searchFilter) {
			case '>':
				Calendar calendarMin = GregorianCalendar.getInstance();
				calendarMin.setTime(date);
				if (secondsPrecision) {
					// El siguiente segundo, no el siguiente milisegundo
					calendarMin.set(Calendar.SECOND, +1);
					calendarMin.set(Calendar.MILLISECOND, -1);
				} else {
					// El siguiente día, no el siguiente milisegundo
					calendarMin.add(Calendar.DAY_OF_YEAR, +1);
					calendarMin.add(Calendar.MILLISECOND, -1);
				}
				dateMin = calendarMin.getTime();
				break;
			case '<':
				dateMax = date;
				break;
			}
		} else {
			Calendar calendarMin = GregorianCalendar.getInstance();
			calendarMin.setTime(date);
			// se resta el mínimo para que la igualdad se cumpla
			calendarMin.add(Calendar.MILLISECOND, -1);
			dateMin = calendarMin.getTime();
			Calendar calendarMax = GregorianCalendar.getInstance();
			calendarMax.setTime(date);
			if (secondsPrecision) {
				// un segundo de margen
				calendarMax.add(Calendar.SECOND, +1);
			} else {
				// un día de margen
				calendarMax.add(Calendar.DAY_OF_YEAR, +1);
			}
			dateMax = calendarMax.getTime();
		}
		return new LimitDates(dateMax, dateMin);
	}
	
	
	/**
	 * Permite transformar una cadena en formato fecha (con o sin hora en formato Date)
	 * @param fecha cadena en formato dd/mm/yyyy [kk:mm:ss]
	 * @param esDataFi Indica si es la fecha fin, para ponerla en el último instante del día indicado
	 * @return
	 * @throws InternalErrorException 
	 */
	public static Date stringToDate (String fecha, boolean esDataFi) throws InternalErrorException {
		Date data=null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"dd/MM/yyyy HH:mm"); //$NON-NLS-1$
			data = dateFormat.parse(fecha);
		} catch (Exception e) {
			try {
				if (fecha.trim().length()!=10) throw new Exception(Messages.getString("DateUtils.YearLenghtError")); //$NON-NLS-1$
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
				data = dateFormat.parse(fecha);
				if (esDataFi){ // Lo ponemos a las 23:59:59... de ese dia
					Calendar cal = Calendar.getInstance();
					cal.setTime(data);
					cal.add(Calendar.DAY_OF_YEAR, +1);
					cal.add(Calendar.MILLISECOND, -1);
					data = cal.getTime();					
				}
			} catch (Exception ex) {
				throw new es.caib.seycon.ng.exception.InternalErrorException(
						String.format(Messages.getString("DateUtils.InvalidShortDate"), //$NON-NLS-1$
								fecha, e.getMessage()));
			}
		}
		return data;
	}
	
	public static String dataToStringFull(Date data) {
		if (data == null) return ""; //$NON-NLS-1$
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		return dateFormat.format(data);
	}

}