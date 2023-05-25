{
	"data": {
		"request": [
			{
				"token": "Macros",
				"MSGTYPE": 200,
				"PRI_BITMAP_TJT": "E23E4401A8E09202",
				"SEC_BITMAP_TJT": 100,
				"NRO_TARJETA_TJT": 4593340002073059,
				"PRI_BITMAP": "E23A440188E18002",
				"SEC_BITMAP": 100,
				"NRO_TARJETA": 8888888800000000,
				"CONSULTA_CTA_CREDITO": 935500,
				"POS_ENTRY_MODE": 51,
				"SOLES": 604,
				"DOLARES": 840,
				"MERCH_TYPE": 5942,
				"ACQ_ID": 7963,
				"FWD_ID": 40404,
				"TERMINAL": 22340880,
				"ACCEPTORID": 299999490000,
				"ACCEPTORNAME": "PROCESOS MC PRUEBAS SA",
				"TRACK_TJTA": "4593340002073059=21042261059813600000",
				"PIN": "01A5A470178C2C4F",
				"EXTORNO_MSGTYPE": "",
				"PRI_BITMAP_EXTORNO": "",
				"SEC_BITMAP_EXTORNO": "",
				"RESP_CODE": 0,
				"EXPIRE_DATE": 2104
			},
			{
				"token": "ReqFinanciero",
				"pan": "{NRO_TARJETA}",
				"txnDate": "dateMMDD()",
				"txnTime": "timeHHMMSS(tmp/mastercard.time)",
				"trace": "sequence(tmp/mastercard.trace;6;1)",
				"localTime": "read(tmp/mastercard.time;A;4)",
				"localDate": "dateMMDD()",
				"expirationDate": "{EXPIRE_DATE}",
				"merchantType": "{MERCH_TYPE}",
				"posEntryMode": "{POS_ENTRY_MODE}",
				"acqId": "{ACQ_ID}",
				"fwdId": "{FWD_ID}",
				"terminal": "{TERMINAL}",
				"dateSettlement": "dateMMDD()",
				"addDataPrivate": 98491260,
				"currencyCode": "{SOLES}",
				"networkData": "CC2000354786",
				"custInfoResponse": 530150299999429900000.0,
				"track2": "{TRACK_TJTA}",
				"iccData": "5F280206045F34010182025C00950500000480009A031910089F02060000000000009F0702FF809F0D05D8688CA8009F0E0524102000409F0F05D868DCF8009F100706010A03A0B8009F26081FF02925CE8731A79F2701809F3303E0F0C89F34030203009F3501229F360204F39F37045F0B6BCF9C01005F2A0200009F1A0206049F5301528407A00000000310105A0842615390337602434F07A00000000310109F0607A0000000031010"
			},
			{
				"token": "ReqConsultaCredito",
				"msgType": "{MSGTYPE}",
				"priBitmap": "{PRI_BITMAP}",
				"secBitmap": "{SEC_BITMAP}",
				"processingCode": "{CONSULTA_CTA_CREDITO}",
				"referenceNumber": "sequence(tmp/mastercard.ref;12;1)",
				"acceptorId": "{ACCEPTORID}",
				"acceptorName": "{ACCEPTORNAME}",
				"pin": "{PIN}"
			},
			{
				"token": "ReqConsultaCreditoTarjeta",
				"msgType": "{MSGTYPE}",
				"priBitmap": "{PRI_BITMAP_TJT}",
				"secBitmap": "{SEC_BITMAP_TJT}",
				"processingCode": "{CONSULTA_CTA_CREDITO}",
				"referenceNumber": "sequence(tmp/mastercard.ref;12;1)",
				"acceptorId": "{ACCEPTORID}",
				"acceptorName": "{ACCEPTORNAME}",
				"pin": "{PIN}",
				"pan": "{NRO_TARJETA_TJT}"
			}
		]
	}
}