package com.corphelper.mailservice.service.impl;

import com.corphelper.mailservice.pojo.FileInfo;
import com.corphelper.mailservice.pojo.MailInfo;
import com.corphelper.mailservice.service.MailCheckerService;
import com.corphelper.mailservice.util.DecoderUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailCheckerServiceImpl implements MailCheckerService {

    private final Folder folder;

    private final DecoderUtil decoderUtil;

    @Value("${javax.mail.user}")
    private String user;

    @Value("${javax.mail.password}")
    private String password;

    @Value("${javax.mail.host}")
    private String host;

    @Value("${javax.mail.folder}")
    private String folderName;


    @Override
    @SneakyThrows
    public List<MailInfo> checkMail() {

        folder.open(Folder.READ_WRITE);
        Flags seen = new Flags(Flags.Flag.SEEN);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);

        log.info("Try to get messages");

        Message messages[] = folder.search(unseenFlagTerm);
        List<Message> messageList = Arrays.asList(messages);
        isMessages(messageList);
        List<MailInfo> mailInfos = findAndSendAttachment(messageList);

        folder.close();
        return mailInfos;

    }

    private void isMessages(List<Message> messageList) {

        if(messageList.isEmpty()){

            log.info("There weren't new messages.");

        }
    }

    private List<MailInfo> findAndSendAttachment(List<Message> messageList) throws IOException, MessagingException {

        List<MailInfo> mailInfos = new ArrayList<>();

        for (Message message : messageList) {

            Multipart multipart = (Multipart) message.getContent();
            MailInfo mailInfo = new MailInfo();
            mailInfo.setSubject(message.getSubject());
            getFromLAndSetToMailInfo(message, mailInfo);
            List<FileInfo> fileInfoList = iterateMimeBodyParts(multipart);
            mailInfo.setFileInfoList(fileInfoList);
            mailInfos.add(mailInfo);

        }

        return mailInfos;
    }

    private void getFromLAndSetToMailInfo(Message message, MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException {

        Address ad = message.getFrom()[0];
        String from = MimeUtility.decodeText(ad.toString());
        mailInfo.setFrom(from);

    }

    private List<FileInfo> iterateMimeBodyParts(Multipart multipart) throws MessagingException, IOException {

        int countOfBodyParts = multipart.getCount();
        List<FileInfo> fileInfoList = new ArrayList<>();

        int countOfAttachments = 0;

        for (int partCount = 0; partCount < countOfBodyParts; partCount++) {

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);

            if (isAttachment(part)) {

                countOfAttachments++;

                log.info("Try to save file");

                FileInfo fileInfo = getFileInfo(part);
                fileInfoList.add(fileInfo);

            }
        }

        isAttachmentsToMessages(countOfAttachments);

        return fileInfoList;
    }

    private void isAttachmentsToMessages(int countOfAttachments) {

        if(countOfAttachments == 0){

            log.info("Messages were without attachments.");

        }else{

            log.info("All attachments were add to list");
        }
    }

    private FileInfo getFileInfo(MimeBodyPart part) throws IOException, MessagingException {

        String fullEncodeFileName = part.getFileName();
        String fullDecodeFileName = decoderUtil.getDecodedString(fullEncodeFileName);
        String fileName = FilenameUtils.getBaseName(fullDecodeFileName);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setExtension(FilenameUtils.getExtension(fullDecodeFileName));
        fileInfo.setFileName(fileName);

        byte[] bytes = IOUtils.toByteArray(part.getInputStream());
        fileInfo.setFileBytes(bytes);

        return fileInfo;
    }


    private boolean isAttachment(MimeBodyPart part) throws MessagingException {

        return Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition());
    }
}

