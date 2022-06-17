package com.corphelper.mailservice.service.impl;

import com.corphelper.mailservice.pojo.FileInfo;
import com.corphelper.mailservice.pojo.MailInfo;
import com.corphelper.mailservice.service.MailCheckerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class MailCheckerServiceImpl implements MailCheckerService {

    private final Folder folder;

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

        System.out.println("Try to get messages");

        Message messages[] = folder.search(unseenFlagTerm);
        List<Message> messageList = Arrays.asList(messages);
        List<MailInfo> mailInfos = findAndSendAttachment(messageList);

        folder.close();
        return mailInfos;

    }

    private List<MailInfo> findAndSendAttachment(List<Message> messageList) throws IOException, MessagingException {

        List<MailInfo> mailInfos = new ArrayList<>();

        for (Message message : messageList) {

            Multipart multipart = (Multipart) message.getContent();
            MailInfo mailInfo = new MailInfo();
            mailInfo.setSubject(message.getSubject());
            getFromLAndSetToMailInfo(message, mailInfo);
            Optional<FileInfo> optionalFileInfo = iterateMimeBodyParts(multipart);
            optionalFileInfo.ifPresent(mailInfo::setFileInfo);
            mailInfos.add(mailInfo);

        }

        return mailInfos;
    }

    private void getFromLAndSetToMailInfo(Message message, MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException {

        Address ad = message.getFrom()[0];
        String from = MimeUtility.decodeText(ad.toString());
        mailInfo.setFrom(from);

    }

    private Optional<FileInfo> iterateMimeBodyParts(Multipart multipart) throws MessagingException, IOException {

        int countOfBodyParts = multipart.getCount();

        for (int partCount = 0; partCount < countOfBodyParts; partCount++) {

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);

            if (isAttachment(part)) {

                System.out.println("Try to save file");
                FileInfo fileInfo = getFileInfo(part);

                return Optional.of(fileInfo);
            }
        }

        return Optional.empty();
    }

    private FileInfo getFileInfo(MimeBodyPart part) throws IOException, MessagingException {

        String fileName = part.getFileName();
        FileInfo fileInfo = new FileInfo();
        fileInfo.setExtension(FilenameUtils.getExtension(fileName));
        fileInfo.setFileName(FilenameUtils.getBaseName(fileName));
        byte[] bytes = IOUtils.toByteArray(part.getInputStream());
        fileInfo.setFileBytes(bytes);

        return fileInfo;
    }

    private boolean isAttachment(MimeBodyPart part) throws MessagingException {

        return Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition());
    }
}

