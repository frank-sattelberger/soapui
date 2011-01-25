/*
 *  soapUI, copyright (C) 2004-2011 eviware.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */
package com.eviware.soapui.security.boundary;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.eviware.soapui.SoapUI;
/**
 * @author nebojsa.tasic
 */
public class BoundaryUtils
{
	
	/**
	 * create string of specified size from random characters specified by availableValues 
	 * @param availableValues
	 * @param size
	 * @return
	 */
	public static String createCharacterArray( String availableValues, Integer size )
	{
		if( size == null )
		{
			SoapUI.log.error( "size is not specified!" );
			return null;
		}
		StringBuilder sb = new StringBuilder( size );
		for( int i = 0; i < size; i++ )
		{
			sb.append( randomCharacter( availableValues ) );
		}
		return sb.toString();
	}

	/**
	 * returns one random character from specified availableValues string
	 * @param availableValues
	 * @return character
	 */
	public static String randomCharacter( String availableValues )
	{
		int position = ( int )( Math.random() * availableValues.length() );
		return availableValues.substring( position, position + 1 );
	}
/**
 * 
 * creates date in string representation that is differs from restrictionDate by daysOffset number of days 
 * @param restrictionDate
 * @param daysOffset
 * @return date
 */
	public static String createDate( String restrictionDate, int daysOffset )
	{
		try
		{
			Date date = DateBoundary.simpleDateFormat.parse( restrictionDate );
			Calendar calendar = Calendar.getInstance();
			calendar.setTime( date );
			calendar.add( Calendar.DAY_OF_YEAR, daysOffset );
			return DateBoundary.simpleDateFormat.format( calendar.getTime() );
		}
		catch( ParseException e )
		{
			SoapUI.logError( e, "date : '" + restrictionDate + "' is not in proper format: " + DateBoundary.DATE_FORMAT );
		}
		return null;
	}
}