package eu.de4a.kafkaclient.model;

import javax.annotation.Nonnull;

import com.helger.commons.annotation.Nonempty;

public enum ELogMessage {
  
  // Request message received on DR from Data Evaluator (used in ConnectorController & RequestController)
  LOG_REQ_IM_LEGACY_DE_DR("log.request.de.dr.legacy", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "01"), 		//DRI01
  LOG_REQ_IM_DE_DR("log.request.de.dr.im", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "02"), 					//DRI02
  LOG_REQ_USI_DE_DR("log.request.de.dr.usi", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "03"), 				//DRI03
  LOG_REQ_SUBSC_DE_DR("log.request.de.dr.subs", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "04"),				//DRI04
  LOG_REQ_LU_DE_DR("log.request.de.dr.lu", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "05"), 					//DRI05
  
  // Response message received on DR from the DT (used in MessageExchangeManager - Response/Notification)
  LOG_RES_IM_LEGACY_DT_DR("log.response.dt.dr.legacy", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "06"),		//DRI06
  LOG_RES_EVIDENCE_DT_DR("log.response.dt.dr.evidence", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "07"),		//DRI07
  LOG_RES_REDIRECT_DT_DR("log.response.dt.dr.redirect", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "08"),		//DRI08
  LOG_RES_SUBSC_DT_DR("log.response.dt.dr.subs", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "09"),			//DRI09
  
  // Request message received on DT from DR (used in MessageExchangeManager - Request)
  LOG_REQ_IM_LEGACY_DR_DT("log.request.dr.dt.legacy", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "01"), 		//DTI01
  LOG_REQ_IM_DR_DT("log.request.dr.dt.im", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "02"),					//DTI02
  LOG_REQ_USI_DR_DT("log.request.dr.dt.usi", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "03"),				//DTI03
  LOG_REQ_LU_DR_DT("log.request.dr.dt.lu", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "04"),					//DTI04
  LOG_REQ_SUBSC_DR_DT("log.request.dr.dt.subs", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "05"),				//DTI05
  
  // Response message received from Connector DT (used in ResponseController)
  LOG_RES_IM_LEGACY_DO_DT("log.response.do.dt.legacy", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "06"),		//DRI06
  LOG_RES_EVIDENCE_DO_DT("log.response.do.dt.evidence", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "07"),		//DTI07
  LOG_RES_REDIRECT_DO_DT("log.response.do.dt.redirect", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "08"),		//DTI08
  LOG_RES_SUBSC_DO_DT("log.response.do.dt.subs", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "09"),			//DTI09
  
  // Notification Event
  LOG_EVENT_NOTIF_DO_DT("log.event.notification.dt", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "10"),		//DTI10
  LOG_EVENT_NOTIF_DT_DR("log.event.notification.dr", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "10"),		//DRI10

  // Message received from Data Owner
  LOG_NOTIF_REQ_RECEIPT("log.request.receipt", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "10"),				//DTI10
  
  // Interactions between DR and SMP/SML/IAL
  LOG_REQ_DR_SMP("log.request.sent.smp", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "11"),					//DRI11
  LOG_REQ_DR_SML("log.request.sent.sml", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "12"),					//DRI12
  LOG_REQ_DR_IAL("log.request.sent.idk", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "13"),					//DRI13
  
  // Interactions between DT and SMP/SML
  LOG_REQ_DT_SMP("log.request.sent.smp", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "11"),					//DTI11
  LOG_REQ_DT_SML("log.request.sent.sml", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "12"),					//DTI12

  // Lookup on de-do.json
  LOG_DR_PARTICIPANT_LOOKUP("log.participant.lookup", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "14"),		//DRI14
  LOG_DT_PARTICIPANT_LOOKUP("log.participant.lookup", EExternalModule.CONNECTOR_DT, ELogMessageLevel.INFO, "14"),		//DTI14
  
  // Error on lookup on de-do.json
  LOG_ERROR_DR_PARTICIPANT_LOOKUP("log.error.participant.lookup", EExternalModule.CONNECTOR_DR, ELogMessageLevel.ERROR, "11"), //DRE11
  LOG_ERROR_DT_PARTICIPANT_LOOKUP("log.error.participant.lookup", EExternalModule.CONNECTOR_DR, ELogMessageLevel.ERROR, "11"), //DTE11
  
  //Message sent to DE from DR
  LOG_REQ_DE("log.request.sent.de", EExternalModule.CONNECTOR_DR, ELogMessageLevel.INFO, "07");
  
  // AS4 Messsages
  //LOG_AS4_MSG_SENT("log.message.sent.as4", EExternalModule.AS4, ELogMessageLevel.INFO, "01"),
  //LOG_AS4_REQ_RECEIPT("log.message.received.as4", EExternalModule.AS4, ELogMessageLevel.INFO, "02");
  
  // Message sent to DO
  /*
  LOG_IM_REQ_DO("log.request.sent.do", ELogMessageType.CLIENT, "02", EExternalModule.CONNECTOR_DT, EExternalModule.DATA_OWNER),
  LOG_USI_REQ_DO("log.request.sent.do", ELogMessageType.CLIENT, "03", EExternalModule.CONNECTOR_DT, EExternalModule.DATA_OWNER),
  LOG_LU_REQ_DO("log.request.sent.do", ELogMessageType.CLIENT, "04", EExternalModule.CONNECTOR_DT, EExternalModule.DATA_OWNER),
  LOG_SN_REQ_DO("log.request.sent.do", ELogMessageType.CLIENT, "05", EExternalModule.CONNECTOR_DT, EExternalModule.DATA_OWNER),
  LOG_REQ_DO("log.request.sent.do", ELogMessageType.CLIENT, "06", EExternalModule.CONNECTOR_DT, EExternalModule.DATA_OWNER),
  */
  
  // Actually don't used by connector component
  /*
  LOG_IM_REQ_PROC("log.request.receipt.imusi", ELogMessageType.SERVICES, "01", EExternalModule.CONNECTOR_DR, EExternalModule.CONNECTOR_DT),
  LOG_USI_REQ_PROC("log.request.receipt.imusi", ELogMessageType.SERVICES, "02", EExternalModule.CONNECTOR_DR, EExternalModule.CONNECTOR_DT),
  LOG_SMP_REQ_SENT("log.request.sent.smp", ELogMessageType.CLIENT, "02", EExternalModule.CONNECTOR_DR, EExternalModule.SMP),
  LOG_USI_DT_REQ_RECEIPT("log.request.receipt.usidt", ELogMessageType.SERVICES, "03", EExternalModule.DATA_OWNER, EExternalModule.CONNECTOR_DT),
  LOG_IDK_REQ_RECEIPT("log.request.receipt.idk", ELogMessageType.SERVICES, "04", EExternalModule.DATA_EVALUATOR, EExternalModule.CONNECTOR_DR),
  LOG_IDK_REQ_SENT("log.request.sent.idk", ELogMessageType.SERVICES, "05", EExternalModule.CONNECTOR_DR, EExternalModule.IDK),
  LOG_AS4_RESP_SENT("log.response.sent.as4", ELogMessageType.AS4, "02", EExternalModule.CONNECTOR_DT, EExternalModule.CONNECTOR_DR),
  LOG_AS4_RESP_RECEIPT("log.response.receipt.as4", ELogMessageType.AS4, "04", EExternalModule.CONNECTOR_DT, EExternalModule.CONNECTOR_DR),
  LOG_ERROR_UNEXPECTED("log.error.unexpected", ELogMessageType.ERROR, "01", EExternalModule.NONE, EExternalModule.NONE),
  LOG_ERROR_AS4_MSG_INVALID("log.error.as4.msg.invalid", ELogMessageType.ERROR, "02", EExternalModule.CONNECTOR_DR, EExternalModule.CONNECTOR_DT),
  LOG_ERROR_AS4_REQ_INCOMING("log.error.as4.req.incoming", ELogMessageType.ERROR, "03", EExternalModule.CONNECTOR_DR, EExternalModule.CONNECTOR_DT),
  LOG_ERROR_AS4_RESP_RECEIPT("log.error.response.missmatch", ELogMessageType.ERROR, "04", EExternalModule.CONNECTOR_DT, EExternalModule.CONNECTOR_DR),
  LOG_ERROR_UNKNOWN_DE("log.error.unknown.de", ELogMessageType.ERROR, "05", EExternalModule.CONNECTOR_DR, EExternalModule.CONNECTOR_DR),
  LOG_ERROR_AS4_RESP_SENDING("log.error.as4.sending.response", ELogMessageType.ERROR, "06", EExternalModule.CONNECTOR_DT, EExternalModule.CONNECTOR_DR),
  LOG_ERROR_AS4_MSG_SENDING("log.error.as4.sending.message", ELogMessageType.ERROR, "07", EExternalModule.CONNECTOR_DT, EExternalModule.CONNECTOR_DR);
  */
 
  private final String key;
  private final String code;
  private final EExternalModule module;
  private final ELogMessageLevel level;
  
  ELogMessage(@Nonnull @Nonempty final String key, @Nonnull final EExternalModule module, 
		  @Nonnull final ELogMessageLevel level, @Nonnull @Nonempty final String code) {
	    this.key = key;
	    this.level = level;
	    this.module = module;
	    this.code = code;
	  }

  @Nonnull
  @Nonempty
  public String getLogCode() {
    return this.module.getID() + this.level.getCode() + this.code;
  }

  @Nonnull
  @Nonempty
  public String getKey() {
    return key;
  }
  
  @Nonnull
  @Nonempty
  public EExternalModule getModule() {
    return module;
  }
}
