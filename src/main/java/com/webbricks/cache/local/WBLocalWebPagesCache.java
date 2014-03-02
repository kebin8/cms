package com.webbricks.cache.local;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.webbricks.cache.WBWebPagesCache;
import com.webbricks.cmsdata.WBArticle;
import com.webbricks.cmsdata.WBWebPage;
import com.webbricks.datautility.AdminDataStorage;
import com.webbricks.datautility.AdminDataStorageFactory;
import com.webbricks.exception.WBIOException;

public class WBLocalWebPagesCache implements WBWebPagesCache {
	private AdminDataStorage dataStorage;
	private Map<String, WBWebPage> localCacheByExternalId;
	private Map<String, WBWebPage> localCacheByName;	
	private static final Object lock = new Object();
	public WBLocalWebPagesCache()
	{
		dataStorage = AdminDataStorageFactory.getInstance();
		try
		{
			Refresh();
		} catch (WBIOException e)
		{
			
		}
	}
	public WBWebPage getByExternalKey(String externalKey) throws WBIOException
	{
		if (localCacheByExternalId != null)
		{
			return localCacheByExternalId.get(externalKey);
		}
		return null;
	}

	@Override
	public void Refresh() throws WBIOException {
		synchronized (lock)
		{
			Map<String, WBWebPage> tempMapByID = new HashMap<String, WBWebPage>();
			Map<String, WBWebPage> tempMapByName = new HashMap<String, WBWebPage>();
			List<WBWebPage> recList = dataStorage.getAllRecords(WBWebPage.class);
			for(WBWebPage item: recList)
			{
				tempMapByID.put(item.getExternalKey(), item);
				tempMapByName.put(item.getName(), item);
			}
			localCacheByExternalId = tempMapByID;
			localCacheByName = tempMapByName;
		}
		
	}

	public WBWebPage get(String pageName) throws WBIOException
	{
		if (localCacheByName != null)
		{
			return localCacheByName.get(pageName);
		}
		return null;
	}

}