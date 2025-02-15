package com.learnspring.communication;

import com.learnspring.communication.entity.Group;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class GroupArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {

        List<String> list= new ArrayList<String>();
        list.add("ak47");
        list.add("umair");
        list.add("ibad");
        return Stream.of(
                Arguments.of(Group.builder()
                        .groupName("Friends Forever")
                        .createdAt(LocalDateTime.now())
                        .members(list)
                        .profilePicture(null).build()),
                Arguments.of(Group.builder()
                        .groupName("College Friends")
                        .createdAt(LocalDateTime.now())
                        .members(list)
                        .profilePicture(null).build()),
                Arguments.of(Group.builder()
                        .groupName("Freelancing")
                        .createdAt(LocalDateTime.now())
                        .members(list)
                        .profilePicture(null).build())


                );
    }
}
