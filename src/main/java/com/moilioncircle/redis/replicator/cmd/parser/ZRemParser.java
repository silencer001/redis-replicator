/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.redis.replicator.cmd.parser;

import com.moilioncircle.redis.replicator.cmd.CommandParser;
import com.moilioncircle.redis.replicator.cmd.impl.ZRemCommand;

import static com.moilioncircle.redis.replicator.cmd.parser.CommandParsers.objToBytes;
import static com.moilioncircle.redis.replicator.cmd.parser.CommandParsers.objToString;

/**
 * @author Leon Chen
 * @since 2.1.0
 */
public class ZRemParser implements CommandParser<ZRemCommand> {


    @Override
    public ZRemCommand parse(Object[] command) {
        int idx = 1, newIdx = 0;
        String key = objToString(command[idx]);
        byte[] rawKey = objToBytes(command[idx]);
        idx++;
        String[] members = new String[command.length - 2];
        byte[][] rawMembers = new byte[command.length - 2][];
        while (idx < command.length) {
            members[newIdx] = objToString(command[idx]);
            rawMembers[newIdx] = objToBytes(command[idx]);
            newIdx++;
            idx++;
        }
        return new ZRemCommand(key, members, rawKey, rawMembers);
    }

}
