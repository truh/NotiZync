/*
    Copyright 2013 Andreas Willinger, Andreas Vogt, Matthias El-Far, Jakob Klepp

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
package notizync.core.api;

/**
 *
 */
public interface INote
{
    /**
     * set title of this note
     * @param title the title to set
     */
    public void setTitle(String title);

    /**
     * set content of this note
     * @param content the content to set
     */
    public void setContent(String content);
    /**
     * @return title of this note
     */
    public String getTitle();

    /**
     * @return content (text) of this note
     */
    public String getContent();
}
